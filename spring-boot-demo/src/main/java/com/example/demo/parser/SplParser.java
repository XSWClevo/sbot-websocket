package com.example.demo.parser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.example.demo.dto.Transformation;
import com.example.demo.utils.FileOperationUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class SplParser {
    public static TransformationConfigTrie trie = new TransformationConfigTrie();
    public static FunctionOrArgsTrie functionTrie = new FunctionOrArgsTrie();
    public static FunctionOrArgsTrie argsTrie = new FunctionOrArgsTrie();

    public static void main(String[] args) {

        Map<String, Object> transformationConfig = FileOperationUtil.getTransformationConfig();
        FileOperationUtil.mockInsert(transformationConfig);

        // 模拟请求
        String mockRequest = "source=\"WinEventLog:*\" | stats count(ip) by count";
        String theLastEndStr = getTheLastEndStr(mockRequest);
        String[] strings = theLastEndStr.split(" ");
        int length = strings.length;
        String key = strings[0];


        // 1. eval command 检验
        Transformation search = trie.search(key);
        if (verifyTheSearchCommand(length, key, search)) return;

        // 走到这里了，说明eval command 关键字是对的
        // 2. 现在存在四种情况
        // 使用map定义出一个规则，map的key等于stats这种关键字，value等于1, 2, 3, 4
        Integer splSyntaxChoose = SPLSyntaxChooseEnum.getSPLSyntaxType(key);

        // 3. 设置参数
        SPLSyntaxParam param = setSPLSyntaxParam(splSyntaxChoose, strings);

        // 4. 执行搜索提示逻辑
        Object apply = SPLSyntaxChooseEnum.getSPLSyntaxChoose(splSyntaxChoose).apply(param);
        log.info("apply: {}", JSON.toJSONString(apply));
        log.info("----------------------------------------");

        // top limit=20 referer
    }

    private static boolean verifyTheSearchCommand(int length, String key, Transformation search) {
        // 这里先使用字典树遍历一次，看看这些keyword 是否需要特殊处理，其实直接字典树查询[0] 号位关键字，
        // 就能得知fun,args是否需要传递
        // 1. 判断传入搜索字符串的规范性
        if (ObjUtil.isEmpty(search)) {
            // 为空只有两种情况
            // 1. 关键字输入不完整
            // 2. 没有这个关键字
            List<String> prefixMatches = trie.getPrefixMatches(key);
            if (CollUtil.isEmpty(prefixMatches)) {
                throw new RuntimeException("查询函数输入错误");
            }
            // 不仅输入了一个关键字
            if (length == 1) {
                // TODO 命令没有输入完整，这里必须返回 "补全" 的信息
                return true;
            }
        }
        return false;
    }

    /**
     * 根据不同的类型设置参数
     */
    private static SPLSyntaxParam setSPLSyntaxParam(Integer type, String[] keys) {
        String evalKey = keys[0];
        SPLSyntaxParam param = new SPLSyntaxParam();
        // 2.1 key fun arg
        // 2.2 key fun
        // 2.3 key arg
        // 2.4 key
        param.setEvalKey(evalKey);
        switch (type) {
            case 1 -> {
                String funAndArgKey = keys[1];
                if (funAndArgKey.contains("(")) {
                    String[] split = funAndArgKey.split("\\(");
                    String funKey = split[0];
                    String argKey = split[1].substring(0, split[1].length() - 1);
                    param.setFunctionKey(funKey);
                    param.setArgKey(argKey);
                } else {
                    String argKey = keys[2];
                    param.setFunctionKey(funAndArgKey);
                    param.setArgKey(argKey);
                }
            }
            case 2 -> {
                String funKey = keys[1];
                param.setFunctionKey(funKey);
            }
            case 3 -> {
                String argKey = keys[2];
                param.setArgKey(argKey);
            }
            case 4 -> log.info("第四步不用写，放这里与注释对应");
        }
        return param;
    }

    private static String getTheLastEndStr(String mockRequest) {
        String[] split = mockRequest.split("\\|");

        if (split.length > 1) {
            // 获取最后一个|之后的所有字符串
            String lastPart = mockRequest.substring(mockRequest.lastIndexOf("|") + 1).trim();
            System.out.println(lastPart);
            return lastPart;
        } else {
            // 如果没有|，则输出整个字符串
            System.out.println(mockRequest);
            return mockRequest;
        }
    }

    public static Object allParams(SPLSyntaxParam param) {
        // List<String> result3 = trie.search("li", "abs", "head");
        SPLSyntaxResult search = trie.search(param.getArgKey(), param.getFunctionKey(), param.getEvalKey());
        return search;
    }

    public static Object evalAndFunParams(SPLSyntaxParam param) {
        return trie.search("", "sum", "stats");
    }

    public static Object evalAndArgParams(SPLSyntaxParam param) {
        return trie.search("limit", "", "head");
    }

}
