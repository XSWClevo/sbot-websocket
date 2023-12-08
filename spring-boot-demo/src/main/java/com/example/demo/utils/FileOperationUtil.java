package com.example.demo.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.dto.Argument;
import com.example.demo.dto.Functions;
import com.example.demo.dto.Transformation;
import com.example.demo.dto.TransformationConfig;
import com.example.demo.parser.FunctionOrArgsTrie;
import com.example.demo.parser.SplParser;
import com.example.demo.parser.TransformationConfigTrie;

import java.nio.charset.Charset;
import java.util.Map;

public class FileOperationUtil {

    private static final String JSON_PATH = "templates/SPLSyntax.json";

    public static Map<String, Object> getTransformationConfig() {
        String splJson = FileUtil.readString(JSON_PATH, Charset.defaultCharset());
        TransformationConfig bean = JSONUtil.parseObj(splJson).toBean(TransformationConfig.class);
        Map<String, Object> map = BeanUtil.beanToMap(bean);
        return map;
    }

    public static void mockInsert(Map<String, Object> transformationConfigMap) {
        // 遍历 Map，并将每个 Transformation 对象插入到 Trie 中
        for (Map.Entry<String, Object> entry : transformationConfigMap.entrySet()) {
            String fullPath = entry.getKey();
            Transformation transformation = (Transformation) entry.getValue();
            SplParser.trie.insert(fullPath, transformation);
            if (transformation != null && transformation.getFunctions() != null) {
                for (Functions function : transformation.getFunctions()) {
                    SplParser.functionTrie.insert(function.getName());
                }
                if (transformation.getArgs() != null) {
                    for (Argument arg : transformation.getArgs()) {
                        SplParser.argsTrie.insert(arg.getKey());
                    }
                }
            }
        }
    }
}
