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
class TestDCircle3<E> extends TestAbsList {

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



        ++size;
    }

    public E remove(int index) {

        rangeCheck(index);

        Node<E> node = first;

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
