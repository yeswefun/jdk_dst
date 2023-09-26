package com.z.test;

/*
双向循环链表
    与双向链表的 add, remove 相似
    add
        index == size
    remove
        size == 1
        size != 1
            类似 双向链表
                index == 0
                index == size-1
 */
class TestDCircle2<E> extends TestAbsList {

    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;

        public Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == size) {
            Node<E> prev = last;
            Node<E> node = new Node<>(elem, prev, first);
            last = node;
            if (prev == null) { // index == 0 && size == 0
                first = node;
                first.prev = first.next = node;
            } else {
                first.prev = prev.next = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(elem, prev, next);
            next.prev = node;
            prev.next = node;
            if (index == 0) { // index == 0 && size != 0
                first = node;
            }
        }

        ++size;
    }

    public E remove(int index) {

        rangeCheck(index);

        Node<E> node;
        if (size == 1) {
            node = first;
            first = last = null;
        } else {
            node = node(index);
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (index == 0) {
                first = next;
                first.prev = last;
            }

            if (index == size-1) {
                last = prev;
                last.next = first;
            }
        }

        --size;
        return node.elem;
    }

    private Node<E> node(int index) {

        rangeCheck(index);

        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = index; i > 0; i--) {
                node = node.prev;
            }
        }
        return node;
    }
}
