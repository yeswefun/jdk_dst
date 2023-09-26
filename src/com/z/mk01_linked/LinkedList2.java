package com.z.mk01_linked;

/*
带虚拟头节点
 */
class LinkedList2<E> {

    private Node<E> dummyHead;  // 指向无效的节点
    private int size;

    public LinkedList2() {
        dummyHead = new Node<>();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E elem) {
        add(0, elem);
    }

    public void addLast(E elem) {
        add(size, elem);
    }

    /*
        在链表的index(0-based)位置添加新的元素e
        在链表中不是一个常用的操作，练习用
     */
    public void add(int index, E elem) {
        // index == size, ok
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("error: index < 0 || index > size");
        }
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //index == 0
        //index != 0
        prev.next = new Node<>(elem, prev.next);
        ++size;
    }

    /*
        对外部不可见，只知道元素类型
     */
    private static class Node<E> {
        public E elem;
        public Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }

        public Node(E elem) {
            this(elem, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

}
