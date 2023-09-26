package com.z.d02_list;

class TestLinked<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
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

        if (index == 0) {
            first = new Node<>(elem, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }

        ++size;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
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

    @Override
    public void clear() {

    }
}
