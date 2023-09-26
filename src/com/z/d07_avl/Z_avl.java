package com.z.d07_avl;

/*
二叉搜索树复杂度分析

平衡 - Balance
    当节点数量固定时，左右子树的高度越接近，这棵二叉树就越平衡(高度越低)
    最理想的平衡，就是像完全二叉树、满二叉树那样，高度是最小的

平衡二叉搜索树 - BBST
    一棵达到适度平衡的二叉搜索树，可以称之为:平衡二叉搜索树

    AVL
    RB
        cpp - STL(map, set)
        java - TreeMap, TreeSet, HashMap, HashSet

基本概念
    平衡因子(Balance Factor): 某结点的左右子树的高度差

    AVL树的特点
        每个节点的平衡因子只可能是1、0、-1 (绝对值≤1,如果超过1,称之为"失衡”)
        每个节点的左右子树高度差不超过1
        搜索、添加、删除的时间复杂度是O(logn)

添加必然发生在度为0或1的节点上
    n - node
    p - parent
    g - grandparent

旋转

afterAdd

计算平衡因子
 */
interface Z_avl {
}
