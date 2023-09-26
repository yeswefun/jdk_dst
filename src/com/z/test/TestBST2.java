package com.z.test;

class TestBST2<E> {

    private static class Node<E> {
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
    }

    public Node<E> predecessor(Node<E> node) {

        if (node == null)
            return node;

        Node<E> prev = node.left;
        if (prev != null) {
            while (prev.right != null)
                prev = prev.right;
            return prev;
        }

        while (node.parent != null && node.parent.left == node)
            node = node.parent;

        //node.parent == null
        //node.parent.right == node
        return node.parent;
    }

    public Node<E> successor(Node<E> node) {

        if (node == null)
            return node;

        Node<E> next = node.right;
        if (next != null) {
            while (next.left != null)
                next = next.left;
            return next;
        }

        while (node.parent != null && node.parent.right == node)
            node = node.parent;
        return node.parent;
    }
}
