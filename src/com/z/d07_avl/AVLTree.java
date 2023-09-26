package com.z.d07_avl;

import java.util.Comparator;

class AVLTree<E> extends BST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /*
        添加节点后，可能会导致失衡
            不失衡
            失衡
                根据 新添加的节点 找到(高度最小的)失衡的节点

        最坏的情况: 可能会导致所有祖先节点都失衡
            父节点，非祖先节点 - 不可能失衡

        新添加的节点必然是叶子节点 - node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) { // node是否平衡，平衡因子
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                // 只要找到第一个不平衡的，让其恢复平衡
                // 整棵树恢复平衡
                break;
            }
        }
    }

    /*
        高度最小的失衡节点 - 整棵的不平衡的起源
        g - grandparent
        p - parent
        n - node
     */
    private void rebalance(Node<E> g) {
        Node<E> p = ((AVLNode<E>) g).tallerChild();
        Node<E> n = ((AVLNode<E>) p).tallerChild();
        // 如何判断是 LL, RR, LR, RL
        if (p.isLeftChild()) { // L
            if (n.isLeftChild()) { // LL
                rotateRight(g);
            } else { // LR
                rotateLeft(p);
                rotateRight(g);
            }
        } else {  // R
            if (n.isRightChild()) { // RR
                rotateLeft(g);
            } else { // RL
                rotateRight(p);
                rotateLeft(g);
            }
        }
    }

    /*
        左旋转
     */
    private void rotateLeft(Node<E> node) {

    }

    /*
        右旋转
     */
    private void rotateRight(Node<E> node) {

    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /*
        是否平衡
     */
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    @Override
    protected Node<E> createNode(E elem, Node<E> parent) {
        return new AVLNode<>(elem, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        /*
            新添加的节点必然是叶子节点
            叶子节点高度为 1
         */
        int height = 1;

        public AVLNode(E elem, Node<E> parent) {
            super(elem, parent);
        }

        /*
            平衡因子
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /*
            更新节点的高度
                节点的高度 = 1 + (左右子树中高度的最大值)
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /*
            左右子树中高度最大的那个节点
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight)
                return left;
            else if (leftHeight < rightHeight)
                return right;
            else // left 和 right 都可以，此处返回的是 g 是其父节点的左还是右
                return isLeftChild() ? left : right;
        }
    }
}
