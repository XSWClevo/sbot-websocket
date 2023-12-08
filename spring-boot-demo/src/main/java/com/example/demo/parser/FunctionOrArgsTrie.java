package com.example.demo.parser;

import java.util.ArrayList;
import java.util.List;

public class FunctionOrArgsTrie {
    // key -> value ; value.function -> args
    private TrieNode root;

    public FunctionOrArgsTrie() {
        this.root = new TrieNode();
    }

    public void insert(String key) {
        TrieNode node = root;
        for (char ch : key.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isWord = true;

    }

    /**
     * 根据前缀获取所有匹配的单词
     *
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
     *
     * @param node          当前节点
     * @param currentPrefix 当前前缀
     * @param result        最后的结果
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
