package com.z.mk_sum;

/*
(了解)SegmentTree, 线段树, 区间树

    线段树虽然不是完全二叉树，但可以将其看作是一棵满二叉树(虽然会浪费一点空间)，
    进而可以使用数组进行存储(与堆存储的形式一致)
        满二叉树 是一种 特殊的完全二叉树

    为什么使用线段树?
        不考虑 添加 和 删除
        只考虑 更新 和 查询


区间染色问题
    m次操作后，我们可以看见多少种颜色?
    m次操作后，我们可以在[i, j]区间内看见多少种颜色?


操作
    创建线段树

    查询线段树
        查询一个区间 [i, j] 的 最大值，最小值，或者 区间数字和
        实质: 基于区间的统计查询

        线段树求和
            不考虑添加和删除, 只考虑更新和查询

    更新线段树的某一个值
        动态更新

递归 - 后序遍历

SegmentTree的时间复杂度分析



线段树不是完全二叉树
平衡二叉树
    线段树
    堆



完全二叉树一定是平衡二叉树

二叉搜索树，添加 或 删除 造成 退化成链表
但平衡二叉树不会出现这种现象
    查询, O(log(n))

满二叉树 是 特殊的完全二叉树
    可以使用数组表示


满二叉树
                100
        30              70
    20      50      60      80


完全二叉树
                100
        30              70
    20      50      60


                100
        30              70
    20      50


                100
        30              70
    20
 */
interface Z90_Seg {
}