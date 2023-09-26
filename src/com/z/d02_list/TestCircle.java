package com.z.d02_list;

class TestCircle<E> extends AbstractList<E> {

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
            Node<E> node = new Node<>(elem, first);
            Node<E> last = (size == 0) ? node : node(size - 1);
            last.next = first = node;
//            if (size == 0) {
//                Node<E> last = node;
//                last.next = first = node;
//            } else {
//                Node<E> last = node(size - 1);
//                last.next = first = node;
//            }
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }

        ++size;
    }

    @Override
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
            Node<E> prev = node(index-1);
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

    @Override
    public void clear() {

    }
}
