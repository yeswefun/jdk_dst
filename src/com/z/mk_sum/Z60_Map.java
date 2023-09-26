package com.z.mk_sum;

/*
Map: 映射，字典
    存储(键，值)数据对的数据结构(Key, Value)
    根据键(Key) ，寻找值(Value)

Map底层实现
    链表
        Node {
            K key
            V value
            Node next;
        }
    二分搜索树
        Node {
            K key
            V value
            Node left
            Node right
        }


Map的时间复杂度分析




有序映射和无序映射
    有序映射中的键具有顺序性(基于 搜索树 的实现)

    无序映射中的键没有顺序性(基于 哈希表 的实现)
        无序映射也可以使用 链表实现，效率低


多重映射
    多重映射中的键可以重复



集合和映射的关系
    通过 Map 实现 Set, V == null, 此时 Map 的 get 和 set 方法都变得没有意义
    Map实现Set,减方法
    Set实现Map,加方法




哈希表
    使用以下数据结构来解决相关问题
        TreeSet, TreeMap
            基于 平衡二叉树 实现
        HashSet, HashMap
            基于 哈希表 实现

面试(重点): 集合 和 映射
 */
interface Z60_Map {
}
