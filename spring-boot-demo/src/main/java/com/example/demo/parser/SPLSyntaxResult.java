package com.example.demo.parser;

import lombok.Data;

import java.util.List;

@Data
public class SPLSyntaxResult {
    /**
     * {
     *   text: 'search index=_internal | timechart count by',
     *   textSplit: ['search', 'index=_internal', 'AS','|', 'timechart', 'count', 'by'],
     *   keyword: ['by'],
     *   function: ['count'],
     *   args: ['_internal'],
     *   strong:['AS']
     * }
     */
    private String text;

    private List<String> textSplit;

    private List<String> keywords;

    private List<String> evalCommands;

    private List<String> functions;

    // 我返回的新字段也放一份到这个集合里面
    private List<String> args;

    private List<String> strong;
}
