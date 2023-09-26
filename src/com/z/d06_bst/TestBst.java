package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTreeInfo;
import com.z.d06_bst.printer.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class TestBst<E> implements BinaryTreeInfo {

    private int size;

    private Node<E> root;

    private Comparator<E> comparator;

    public TestBst() {
        this(null);
    }

    public TestBst(Comparator<E> comparator) {
        this.comparator = comparator;
    }

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
        return ((Node<E>) node).elem;
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

    private void ensureElemNotNull(E elem) {
        if (elem == null)
            throw new IllegalArgumentException("elem cannot be null");
    }

    public void add(E elem) {

        ensureElemNotNull(elem);

//        if (root == null) {
//            root = new Node<>(elem, null);
//            ++size;
//            return;
//        }

        int ret = 0;
        Node<E> parent = null;
        Node<E> tmp = root;
        while (tmp != null) {
            parent = tmp;
            ret = compare(elem, tmp.elem);
            if (ret > 0) {
                tmp = tmp.right;
            } else if (ret < 0) {
                tmp = tmp.left;
            } else {
                tmp.elem = elem;
                return;
            }
        }

        Node<E> newNode = new Node<>(elem, parent);
        if (parent == null) {
            root = newNode;
        } else {
            if (ret > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        ++size;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }


    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        //System.out.print(root.elem + " ");
        visitor.visit(root.elem);
        preorderTraversal(root.left, visitor);
        preorderTraversal(root.right, visitor);
    }

    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        inorderTraversal(root.left, visitor);
        //System.out.print(root.elem + " ");
        visitor.visit(root.elem);
        inorderTraversal(root.right, visitor);
    }

    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        postorderTraversal(root.left, visitor);
        postorderTraversal(root.right, visitor);
        //System.out.print(root.elem + " ");
        visitor.visit(root.elem);
    }

    public void levelorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            //System.out.print(node.elem + " ");
            visitor.visit(node.elem);
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }
    }

    public interface Visitor<E> {
        void visit(E elem);
    }

    static Integer[] data = {
            7, 4, 9, 2, 5, 8, 11, 3,
            12, 1
    };

    public static void main(String[] args) {
        TestBst<Integer> bst = new TestBst<>();
        for (Integer i : data) {
            bst.add(i);
        }
        BinaryTrees.println(bst);

        Visitor<Integer> v = new Visitor<Integer>() {
            @Override
            public void visit(Integer elem) {
                System.out.print(elem + " ");
            }
        };
        System.out.println();
        bst.preorderTraversal(v);

        System.out.println();
        bst.inorderTraversal(v);

        System.out.println();
        bst.postorderTraversal(v);

        System.out.println();
        bst.levelorderTraversal(v);
    }
}
