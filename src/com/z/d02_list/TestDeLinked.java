package com.z.d02_list;

class TestDeLinked<E> extends AbstractList<E> {

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

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E elem) {
        return null;
    }

    @Override
    public int indexOf(E elem) {
        return 0;
    }

    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == size) {

        } else {

        }

        ++size;
    }

    @Override
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
                node = node.next;
            }
        }

        return node;
    }

    @Override
    public void clear() {

    }
}
