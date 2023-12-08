package com.example.demo.parser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.dto.Argument;
import com.example.demo.dto.Transformation;

import java.util.*;

public class TransformationConfigTrie {
    // key -> value ; value.function -> args

    private TrieNode root;

    private Map<String, Transformation> value;

    public TransformationConfigTrie() {
        this.root = new TrieNode();
        this.value = new HashMap<>();
    }

    public void insert(String key, Transformation value) {
        TrieNode node = root;
        for (char ch : key.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isWord = true;
        this.value.put(key, value);
    }

    public Transformation search(String key) {
        TrieNode node = root;
        for (char ch : key.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return null; // Key not found
            }
        }
        return this.value.get(key);
    }

    /**
     * 返回示例：
     *   {
     *     text: 'search index=_internal | timechart count by',
     *     textSplit: ['search', 'index=_internal', 'AS','|', 'timechart', 'count', 'by'],
     *     keyword: ['by'],
     *     function: ['count'],
     *     args: ['_internal'],
     *     strong:['AS']
     *   }
     * @param argName
     * @param functionName
     * @param key
     * @return
     */
    public SPLSyntaxResult search(String argName, String functionName, String key) {
        TrieNode node = root;
        for (char ch : key.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return null; // Key not found
            }
        }
        SPLSyntaxResult result = new SPLSyntaxResult();
        Transformation transformation = this.value.get(key);
        List<String> prefixMatches = null;
        if (node.isWord && ObjUtil.isNotEmpty(transformation)) {
            // 最终节点，开始寻找fun关键字
            if (CollUtil.isNotEmpty(transformation.getFunctions()) && StrUtil.isNotEmpty(functionName)) {
                prefixMatches = SplParser.functionTrie.getPrefixMatches(functionName);
                if (CollUtil.isEmpty(prefixMatches)) {
                    return result;
                }
                if (prefixMatches.size() == 1 && prefixMatches.get(0).equals(functionName)) {
                    // 1. eval command 多了后，这里貌似需要使用递归来处理，因为要获取所以eval command的function
                    // 2. 或者让前端将之前返回的对象再次传过来，然后我直接将本次的结果追加上去
                    result.setFunctions(Collections.singletonList(functionName));

                    // 这里貌似不需要去获取arg
                    // 最终的fun关键字，开始查找args
                    if (StrUtil.isBlank(argName)) {
                        // args没有填写，返回示例
                        prefixMatches = transformation.getArgs().stream().map(Argument::getKey).toList();
                        result.setArgs(prefixMatches);
                    } else {
                        // args填写了，可能是用户自定义的字段，所有这里不做查询
                        // TODO 这个逻辑后面得要删除
                        prefixMatches = SplParser.argsTrie.getPrefixMatches(argName);
                        if (CollUtil.isEmpty(prefixMatches)) {
                            prefixMatches = transformation.getArgs().stream().map(Argument::getKey).toList();
                        }
                    }
                } else {
                    result.setFunctions(prefixMatches);
                }
            } else if (CollUtil.isNotEmpty(transformation.getArgs())) {
                // 进来这里表示这个表达式没有function，但是有args
                // 示例：top limit=20 referer
                prefixMatches = SplParser.argsTrie.getPrefixMatches(argName);
                result.setArgs(prefixMatches);
            }

        } else {
            // 不是最终节点，返回当前匹配的关键字 eval command
            prefixMatches = getPrefixMatches(key);
            result.setEvalCommands(prefixMatches);
        }

        return result;

    }

    /**
     * 根据前缀获取所有匹配的单词
     * @param prefix 前缀
     * @return 匹配的单词
     */
    public List<String> getPrefixMatches(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        // 遍历到指定前缀的最后一个节点
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return result; // 如果前缀不在字典树中，返回空列表
            }
            // 从上往下获取最后一个node
            node = node.children.get(ch);
        }

        // 递归获取以该节点为根的子树的所有单词
        collectWords(node, prefix, result);
        return result;
    }

    /**
     * 获取所有的单词
     * @param node 当前节点
     * @param currentPrefix 当前前缀
     * @param result 最后的结果
     */
    private void collectWords(TrieNode node, String currentPrefix, List<String> result) {
        if (node.isWord) {
            result.add(currentPrefix);
        }

        for (char ch : node.children.keySet()) {
            collectWords(node.children.get(ch), currentPrefix + ch, result);
        }
    }



}
