package com.z.d02_list;

class TestDLinked<E> extends AbstractList<E> {

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
            // 在尾节点后添加一个节点
            // 添加第一个节点
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
        return null;
    }

    @Override
    public void clear() {

    }
}
