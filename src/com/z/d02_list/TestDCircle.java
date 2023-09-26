package com.z.d02_list;

class TestDCircle<E> extends AbstractList<E> {

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
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(elem, prev, next);
            next.prev = node;
            prev.next = node;
            if (index == 0) {
                // 为什么在此不需要处理 last
                first = node;
            }
        }

        ++size;
    }

    @Override
    public E remove(int index) {

        rangeCheck(index);

        Node<E> node;
        if (size == 1) { // last == first
            node = first;
            first = last = null;
        } else {
            node = node(index);
            Node<E> next = node.next;
            Node<E> prev = node.prev;

            prev.next = next;
            next.prev = prev;

            if (index == 0) {
                first = next;
                first.prev = last;
            }
            if (index == size - 1) {
                last = prev;
                last.next = first;
            }
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

    @Override
    public void clear() {

    }
}
