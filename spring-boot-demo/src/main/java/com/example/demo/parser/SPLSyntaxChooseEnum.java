package com.example.demo.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum SPLSyntaxChooseEnum {
    DEDUP("dedup", 1),
    FIELDS("fields", 1),
    HEAD("head", 1),
    JOIN("join", 1),
    LOOKUP("lookup", 1),
    REGEX("regex", 1),
    RENAME("rename", 1),
    SEARCH("search", 1),
    SORT("sort", 1),
    STATS("stats", 1)
    ;

    private final String key;

    private final Integer value;

    private static final Map<String, Integer> map = new HashMap<>();

    private static final Map<Integer, Function<SPLSyntaxParam, Object>> functionMap = new HashMap<>();

    static {
        for (SPLSyntaxChooseEnum value : SPLSyntaxChooseEnum.values()) {
            map.put(value.getKey(), value.getValue());
        }
        functionMap.put(1, SplParser::allParams);
        functionMap.put(2, SplParser::evalAndFunParams);
        functionMap.put(3, SplParser::evalAndArgParams);

    }

    SPLSyntaxChooseEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static Integer getSPLSyntaxType(String key) {
        return map.get(key);
    }

    public static Function<SPLSyntaxParam, Object> getSPLSyntaxChoose(Integer key) {
        return functionMap.get(key);
    }
}
