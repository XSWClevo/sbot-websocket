package com.example.demo.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie树（前缀树、字典树）
 */
public class Trie {

    private Node root;
    private int size;

    private final Map<String, Map<String, String>> commandInfo;  // 存储命令的详细信息
    private final Map<String, Map<String, String>> functionInfo; // 存储函数的详细信息


    public Trie() {
        root = new Node();
        size = 0;
        commandInfo = new HashMap<>();
        functionInfo = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加一个单词
     */
    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 添加一个单词或
     * @param word 单词
     * @param info 单词的含义
     */
    public void add(String word, Map<String, String> info) {
        if (word == null || word.length() == 0) {
            return;
        }
        Node node = root;
        for (char ch : word.toCharArray()) {
            node.next.putIfAbsent(ch, new Node());
            node = node.next.get(ch);
        }
        node.isWord = true;
        size++;
        commandInfo.put(word, info);
    }

    public void insertFunction(String command, Map<String, String> info) {
        info.forEach((key, value) -> functionInfo.computeIfAbsent(command, k -> new HashMap<>()).put(key, value));
    }

    public Map<String, String> getCommandInfo(String command) {
        return commandInfo.get(command);
    }

    public Map<String, String> getFunctionInfo(String command) {
        return functionInfo.getOrDefault(command, new HashMap<>());
    }


    /**
     * 递归添加一个单词
     */
    public void addRecursion(String word) {
        root = addRecursion(root, word);
    }

    /**
     * 在node节点添加一个单词
     */
    private Node addRecursion(Node node, String word) {
        if (node == null) {
            return null;
        }
        if (word == null || word.length() == 0) {
            return node;
        }
        if (node.next.get(word.charAt(0)) == null) {
            node.next.put(word.charAt(0), new Node());
        }
        // 这里的含义是：在node.next.get(word.charAt(0))节点中添加word.substring(1)这个单词
        // 例如单词为start, 那么k, v 就是 s, tart. 也就是说在s节点中添加tart这个单词,依次递归 后续的单词
        node.next.put(word.charAt(0), addRecursion(node.next.get(word.charAt(0)), word.substring(1)));
        // 当单词为单个字符并且是最后一个的时候，给最终节点标识
        if (word.length() == 1 && !node.next.get(word.charAt(0)).isWord) {
            node.next.get(word.charAt(0)).isWord = true;
            size++;
        }
        return node;
    }

    /**
     * 是否包含某个单词
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 递归查询是否包含某个单词
     */
    public boolean containsRecursion(String word) {
        return containsRecursion(root, word);
    }

    /**
     * 递归查询某个节点中是否包含某个单词
     */
    private boolean containsRecursion(Node node, String word) {
        if (node == null) {
            return false;
        }
        if (word.length() > 0) {
            if (node.next.get(word.charAt(0)) == null) {
                return false;
            } else if (word.length() == 1 && node.next.get(word.charAt(0)).isWord) {
                return true;
            }
            return containsRecursion(node.next.get(word.charAt(0)), word.substring(1));
        }
        return false;
    }

    /**
     * 是否包含此前缀的单词
     */
    private boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }


    /**
     * 递归查询是否包含此前缀的单词
     */
    public boolean isPrefixRecurison(String word) {
        return isPrefixRecurison(root, word);
    }

    /**
     * 递归查询是否包含此前缀的单词
     */
    private boolean isPrefixRecurison(Node node, String word) {
        if (node == null) {
            return false;
        }
        if (word.length() > 0) {
            if (node.next.get(word.charAt(0)) == null) {
                return false;
            } else if (word.length() == 1) {
                return true;
            }
            return isPrefixRecurison(node.next.get(word.charAt(0)), word.substring(1));
        }
        return false;
    }

    /**
     * 根据前缀获取所有匹配的单词
     * @param prefix 前缀
     * @return 匹配的单词
     */
    public List<String> getPrefixMatches(String prefix) {
        List<String> result = new ArrayList<>();
        Node node = root;

        // 遍历到指定前缀的最后一个节点
        for (char ch : prefix.toCharArray()) {
            if (!node.next.containsKey(ch)) {
                return result; // 如果前缀不在字典树中，返回空列表
            }
            // 从上往下获取最后一个node
            node = node.next.get(ch);
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
    private void collectWords(Node node, String currentPrefix, List<String> result) {
        if (node.isWord) {
            result.add(currentPrefix);
        }

        for (char ch : node.next.keySet()) {
            collectWords(node.next.get(ch), currentPrefix + ch, result);
        }
    }

    /**
     * 节点类
     */
    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
}