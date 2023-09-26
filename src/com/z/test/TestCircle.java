package com.z.test;

/*
单向循环链表
    与单向链表的 add, remove 类似
    add
        index == 0
            size == 0
    remove
        index == 0
            size == 1
 */
class TestCircle<E> extends TestAbsList {

    private Node<E> first;

    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    /*
        index == 0
            Node<E> node = new Node<>(elem, first);
            if (size == 0) {
                Node<E> last = node;
                last.next = first = node;
            } else {
                Node<E> last = node(size - 1);
                last.next = first = node;
            }
     */
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == 0) {
            Node<E> node = new Node<>(elem, first);
            Node<E> last = size == 0 ? node : node(index - 1);
            last.next = first = node;
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
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                last.next = first = node.next;
            }
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
