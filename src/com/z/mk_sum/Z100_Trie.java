package com.z.mk_sum;

/*
Trie, 字典树，前缀树
    通常只用来处理字符串，而字典的值可以是任何类型
    与字符串个数无关，与字符串长度有关


BSTSet 与 Trie 性能比较
    字典
        如果有n个条目
        使用树结构(平衡二叉树)
        查询的时间复杂度是O(logn)
        如果有100万个条目(2^20)
        logn大约为20

    Trie
        查询每个条目的时间复杂度, 和字典中一共有多少条目无关
        时间复杂度为O(w)
            w为查询单词的长度!
            大多数单词的长度小于10


结构
    每个节点有若干指向下个节点的指针
    区分是否为一个单词


添加, add
    app, apple
    teen, teenager
查询, search
前缀搜索, prefix
简单模式匹配, match
词频统计
 */
interface Z100_Trie {
}
