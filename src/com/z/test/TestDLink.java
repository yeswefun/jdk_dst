package com.z.test;

/*
双向链表
    add
        index == size
    remove
        所有情况
            前驱 != null, 后继 != null
            前驱 != null, 后继 == null
            前驱 == null, 后继 != null
            前驱 == null, 后继 == null
 */
class TestDLink<E> extends TestAbsList {

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
            // index == size
            //      size != 0, 最后一个位置
            //      size == 0，第一个元素
            Node<E> prev = last;
            Node<E> node = new Node<>(elem, prev, null);
            last = node;
            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(elem, prev, next);
            next.prev = node;
            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
        }

        ++size;
    }

    public E remove(int index) {

        rangeCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
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
