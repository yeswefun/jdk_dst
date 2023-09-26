package com.z.mk09_trie;

import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.cn/problems/design-add-and-search-words-data-structure/

请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：
    WordDictionary()
        初始化词典对象
    void addWord(word)
        将 word 添加到数据结构中，之后可以对它进行匹配
    bool search(word)
        如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
        word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。


示例：
    输入：
        ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    输出：
        [null,null,null,null,false,true,true,true]

解释：
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.search("pad"); // 返回 False
    wordDictionary.search("bad"); // 返回 True
    wordDictionary.search(".ad"); // 返回 True
    wordDictionary.search("b.."); // 返回 True

提示：
    1 <= word.length <= 25
    addWord 中的 word 由小写英文字母组成
    search 中的 word 由 '.' 或小写英文字母组成
    最多调用 104 次 addWord 和 search
 */
class _211_添加与搜索单词_数据结构设计 {

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

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    private static class WordDictionary {

        private Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node cur = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }

        /*
            模式匹配
                app
                apple
         */
        private boolean match(Node node, String word, int index) {
            if (index == word.length()) {
                return node.isWord;
            }
            char c = word.charAt(index);
            if (c != '.') {
                if (node.next.get(c) == null) {
                    return false;
                }
                return match(node.next.get(c), word, index + 1);
            } else {
                // "." 是 get 不到任何东西的
                for (Character key : node.next.keySet()) {
                    if (match(node.next.get(key), word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }

        public boolean search(String word) {
            return match(root, word, 0);
        }

        public boolean search2(String word) {
            Node cur = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null || c != '.') {
                    return false;
                }
                //特殊处理, '.' 获取不出任何东西， 每个 '.' 都可以表示任何一个字母
                cur = cur.next.get(c);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // 返回 False
        System.out.println(wordDictionary.search("bad")); // 返回 True
        System.out.println(wordDictionary.search(".ad")); // 返回 True
        System.out.println(wordDictionary.search("b..")); // 返回 True
    }
}
