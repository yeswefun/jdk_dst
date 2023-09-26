package com.z.mk09_trie;

import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.cn/problems/map-sum-pairs/

设计一个 map ，满足以下几点:
    字符串表示键，整数表示值
    返回具有前缀等于给定字符串的键的值的总和

实现一个 MapSum 类：

    MapSum()
        初始化 MapSum 对象
    void insert(String key, int val)
        插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。
        如果键 key 已经存在，那么原来的键值对 key-value 将被替代成新的键值对。
    int sum(string prefix)
        返回所有以该前缀 prefix 开头的键 key 的值的总和。

示例 1：
    输入：
        ["MapSum", "insert", "sum", "insert", "sum"]
        [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
    输出：
        [null, null, 3, null, 5]

解释：
    MapSum mapSum = new MapSum();
    mapSum.insert("apple", 3);
    mapSum.sum("ap");           // 返回 3 (apple = 3)
    mapSum.insert("app", 2);
    mapSum.sum("ap");           // 返回 5 (apple + app = 3 + 2 = 5)

提示：
    1 <= key.length, prefix.length <= 50
    key 和 prefix 仅由小写英文字母组成
    1 <= val <= 1000
    最多调用 50 次 insert 和 sum
 */
class _677_键值映射 {

    private static class Node {

        //0表示不是单词，非0表示是单词
        private int value;
        private Map<Character, Node> next;

        public Node() {
            this(0);
        }

        public Node(int value) {
            this.value = value;
            this.next = new TreeMap<>();
        }
    }

    /**
     * Your MapSum object will be instantiated and called as such:
     * MapSum obj = new MapSum();
     * obj.insert(key,val);
     * int param_2 = obj.sum(prefix);
     */
    private static class MapSum {

        private Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = this.root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.value = val;
        }

        public int sum(String prefix) {
            Node cur = this.root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null) {
                    return 0;
                }
                cur = cur.next.get(c);
            }
            //以 cur 为根节点的 单词串
            //app
            //apple
            return sum(cur);
        }

        private int sum(Node node) {
            /*
                if (node.next.size() == 0) {
                    return node.value;
                }
             */
            int ret = node.value;
            for (Character c : node.next.keySet()) { // size == 0
                ret += sum(node.next.get(c));
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));           // 返回 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));           // 返回 5 (apple + app = 3 + 2 = 5)

        //extra
        System.out.println(mapSum.sum("app"));          // 返回 5
    }
}
