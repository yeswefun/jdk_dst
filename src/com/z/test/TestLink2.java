package com.z.test;

/*
单向链表 - 不带头节点
    add, remove
        index == 0
 */
class TestLink2<E> extends TestAbsList {

    private Node<E> first;

    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == 0) {
            first = new Node<>(elem, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }

        ++size;
    }

    public E remove(int index) {

        rangeCheck(index);

        Node<E> node;
        if (index == 0) {
            node = first;
            first = node.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        --size;
        return node.elem;
    }

    private Node<E> node(int index) {

        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }
}
