package com.example.demo.parser;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean isWord;

    public TrieNode(boolean isWord) {
        this.isWord = isWord;
        this.children = new HashMap<>();
    }

    public TrieNode() {
        this(false);
    }

}
