package com.z.mk_sum;

/*
树, Tree


树的分类
    满二叉树
        除了叶子节点，其它点都有两个子节点

                    100
            30              70
        20      50      60      80


    完全二叉树
        不一定是满二叉树
        按层从左往右进行排列，不满的层中所有叶子节点都是从左往右排列

                    100
            30              70
        20      50      60


    满二叉树 是 特殊的完全二叉树
        可以使用数组表示


二叉搜索树(Binary Search Tree, BST):
    二叉搜索树的元素必须具有可比较性

    node
        node == root
        node != root
        递归的出口可以统一上面两种情况

    add

    contains

    遍历
        preOrder，递归
            前序遍历
                先访问根节点，然后访问左子树，再访问右子树

        inOrder，递归
            中序遍历
                先访问左子树，然后访问根节点，再访问右子树

        postOrder，递归
            后序遍历
                先访问左子树，然后访问右子树，再访问根节点


        注: 二叉搜索树的中序遍历结果是有序的


        preOrder，非递归
            栈(LIFO, 注意此处左右子树的顺序)
            深度遍历，DFS

        levelOrder
            队列(FIFO)
            广度遍历，BFS

    minimum
        非递归
        递归

    maximum
        非递归
        递归

    removeMin

    removeMax

    remove



Tree的时间复杂度分析


其它
    前驱和后继肯定存在BST中
    floor和ceil不一定存在BST中


集合和映射
    链表实现
    二叉树实现
 */
interface Z20_Tree {
}
