package com.z.mk09_trie;

import java.util.Map;
import java.util.TreeMap;

class Trie {

    private static class Node {

        public boolean isWord;
        private Map<Character, Node> next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /*
        向Trie中添加一个新的单词word
        补充:递归写法
     */
    public void add(String word) {
        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) { //teenager -> teen
            cur.isWord = true; // cur不一定是叶子节点
            size++;
        }
    }

    /*
        查询单词word是否在Trie中
        补充:递归写法
     */
    public boolean contains(String word) {
        Node cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        //query teen, but teenager
        //query app, but apple
        return cur.isWord;
    }

    /*
        查询是否在Trie中有单词以prefix为前缀
            BSTSet#isPrefix
     */
    public boolean isPrefix(String prefix) {
        Node cur = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        //teen, teenager
        //app, apple
        return true;
    }
}
