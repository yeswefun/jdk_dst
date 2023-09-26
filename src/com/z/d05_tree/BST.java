package com.z.d05_tree;

import java.util.Comparator;

/*
BST: Binary Search Tree

implements BinaryTreeInfo
    为了打印二叉树
 */
class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /*
        do {
            //...
        } while(node != null);
     */
    public void add(E elem) {
        elemNotNullCheck(elem);

        if (root == null) {
            root = new Node<>(elem, null);
            ++size;
            return;
        }

        int ret = 0;
        Node<E> parent = root;
        Node<E> node = root;
        while (node != null) { // 上来的第一次就肯定成立
            parent = node;
            ret = compare(elem, node.elem);
            if (ret > 0) { // 右边，大
                node = node.right;
            } else if (ret < 0) { // 左边，小
                node = node.left;
            } else { // ret == 0，相等
                //return; // 相等直接返回
                node.elem = elem; //其它字段发生了修改
                return;
            }
        }

        Node<E> newNode = new Node<>(elem, parent);
        if (ret > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        ++size;
    }

    /*
        先根据 元素 查找 节点，再将 节点 给删除
     */
    public void remove(E elem) {
        remove(node(elem));
    }

    private void remove(Node<E> node) {
        if (node == null)
            return;

        --size;

        if (node.hasTwoChildren()) { // 度为2
            Node<E> s = successor(node);
            //用前驱或后继节点的值覆盖原节点的值
            node.elem = s.elem;
            //然后删除相应的前驱或后继节点
            node = s;
        }

        //度为0或1，删除node节点
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // 度为1
            replacement.parent = node.parent;
            if (node.parent == null) { // node 度为0，叶子节点且不为root节点
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            }
        } else if (node.parent == null) { // 度为0，叶子节点且为root节点
            root = null;
        } else { // 度为0，叶子节点且不为root节点
            // 判断 node 是 node.parent 的左边还是右边
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E elem) {
        Node<E> node = root;
        while (node != null) {
            int ret = compare(elem, node.elem);
            if (ret > 0) {
                node = node.right;
            } else if (ret < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean contains(E elem) {
        return node(elem) != null;
    }

    /*
        0 : e1 == e2
        >0: e1 > e2
        <0: e1 < e2
     */
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        //Comparable
        //class BinarySearchTree<E extends Comparable<E>> {}
        //return e1.compareTo(e2);

        //Comparator
        //return comparator.compare(e1, e2);

        //先 Comparable，后 Comparator
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elemNotNullCheck(E elem) {
        if (elem == null) {
            throw new IllegalStateException("elem cannot be null");
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        toString(root, sb, "");
//        return sb.toString();
//    }
//
//    private void toString(Node<E> node, StringBuilder sb, String prefix) {
//        if (node == null)
//            return;
//        toString(node.left, sb, prefix + "L***");
//        sb.append(prefix).append(node.elem).append("\n");
//        toString(node.right, sb, prefix + "R---");
//    }
}
