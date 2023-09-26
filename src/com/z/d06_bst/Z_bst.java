package com.z.d06_bst;

/*
          ┌──7(null)──┐
          │           │
      ┌─4(7)─┐     ┌─9(7)─┐
      │      │     │      │
  ┌─2(4)─┐  5(4) 8(9)   11(9)─┐
  │      │                    │
1(2)    3(2)                12(11)

遍历中的 序 是相对于 根节点

preorder: 先访问根节点，再访问左子树，最后访问右子树
    7 4 2 1 3 5 9 8 11 12

inorder: 先访问左子树，再访问根节点，最后访问右子树
    1 2 3 4 5 7 8 9 11 12

postorder: 先访问左子树，再访问右子树，最后访问根节点
    1 3 2 5 4 8 12 11 9 7

levelorder: 先访问根节点，再访问左子树，最后访问右子树
    7 4 9 2 5 8 11 1 3 12

注: 以上左右子树的访问顺序可以互换

实现方法
    add
    compare
        Comparable
        Comparator
    traversal
        preorder
        inorder
        postorder
        levelorder
    height
    isComplete

推导二叉树
    前序 + 中序
    后序 + 中序

前驱节点
    中序遍历目标的前一个节点
    二叉搜索树，根节点的前驱节点是左子树的最大节点

后继节点
    中序遍历目标的后一个节点
    二叉搜索树，根节点的前驱节点是右子树的最小节点

二叉树 - remove
    被删除节点为叶子节点 - 直接删除
        node == 左子节点
            node == node.parent.left
            node.parent.left = null

        node == 右子节点
            node == node.parent.right
            node.parent.right = null

        node == 根节点
            node.parent == null
            root = null

    度为1的节点 - 用子节点替代原节点的位置
        child == node.left || child == node.right

        node == 左子节点
            child.parent = node.parent
            node.parent.left = child

        node == 右子节点
            child.parent = node.parent
            node.parent.right = child

        node == 根节点
            root = child
            child.parent = null

    度为2的节点 - 用前驱或后继节点的值覆盖原节点的值，然后删除相应的前驱或后继节点

        如果一个节点的度为2，那么它的前驱，后继节点的度只可能是 1 和 0

        node == 根节点
            二叉搜索树， 左子树中的最大(前驱) 或 右子树中的最小(后继)

clear

contains

添加，删除，搜索
    O(h) == O(logn) 满二叉树
 */
interface Z_bst {
}
