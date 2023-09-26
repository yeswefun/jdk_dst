package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
BST: Binary Search Tree

implements BinaryTreeInfo
    为了打印二叉树
 */
class BinarySearchTree2<E> implements BinaryTreeInfo {

    private int size;

    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree2() {
        this(null);
    }

    public BinarySearchTree2(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E elem, Node<E> parent) {
            this.elem = elem;
            this.parent = parent;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void elemNotNullCheck(E elem) {
        if (elem == null) {
            throw new IllegalStateException("elem cannot be null");
        }
    }

    /*
        do {
            //...
        } while(node != null);
     */
    public void add(E elem) {

        elemNotNullCheck(elem);

        // 创建根节点
        if (root == null) {
            root = new Node<>(elem, null);
            ++size;
            return;
        }

        // 创建非根节点
        int ret = 0;
        Node<E> parent = root; // 记录父节点
        Node<E> node = root;   // 用来遍历用的，为了找出父节点
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
        前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null)
            return;
        System.out.print(node.elem + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /*
        中序遍历
        可以尝试将 node.left 与 node.right 上下交换一下
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null)
            return;
        inorderTraversal(node.left);
//        inorderTraversal(node.right);
        System.out.print(node.elem + " ");
//        inorderTraversal(node.left);
        inorderTraversal(node.right);
    }

    /*
        后序遍历
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null)
            return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.elem + " ");
    }

    /*
        层序遍历
     */
    public void levelorderTraversal() {
        if (root == null)
            return;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            if (node != null) {
                System.out.print(node.elem + " ");
                q.offer(node.left);
                q.offer(node.right);
            }
        }
    }

    public void levelorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null)
            return;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            //System.out.print(node.elem + " "); // 访问逻辑
            visitor.visit(node.elem);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    /*
        0 : e1 == e2
        >0: e1 > e2
        <0: e1 < e2
     */
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
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

    /*
        节点 Node 对外不可见，可见的是 elem
     */
    public interface Visitor<E> {
        void visit(E elem);
    }

    /*
        BinaryTreeInfo
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> n = (Node<E>) node;
        String parentString;
        if (n.parent == null) {
            parentString = "null";
        } else {
            parentString = n.parent.elem.toString();
        }
        return n.elem + "(" + parentString + ")";
    }
}
