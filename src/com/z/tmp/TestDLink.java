package com.z.tmp;

import com.z.test.TestAbsList;

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
