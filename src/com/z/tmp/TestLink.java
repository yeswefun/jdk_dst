package com.z.tmp;

import com.z.test.TestAbsList;

/*
单向链表 - 不带头节点
    add, remove
        index == 0
 */
class TestLink<E> extends TestAbsList {

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

        ++size;
    }

    public E remove(int index) {

        rangeCheck(index);

        Node<E> node = null;

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
