package com.z.mk01_linked;

/*
不带虚拟头节点
 */
class LinkedList<E> {

    private Node<E> head; // 指向有效的节点
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
        头插法
            Node<E> node = new Node<>(elem);
            node.next = head;
            head = node;
     */
//    public void addFirst(E elem) {
//        head = new Node<>(elem, head);
//        ++size;
//    }
    public void addFirst(E elem) {
        add(0, elem);
    }

    public void addLast(E elem) {
        add(size, elem);
    }

    /*
        在链表的index(0-based)位置添加新的元素e
        在链表中不是一个常用的操作，练习用

        index == 0
            head = new Node<>(elem, head);
        index > 0
            prev.next = new Node<>(elem, prev.next);
     */
    public void add(int index, E elem) {
        // index == size, ok
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("error: index < 0 || index > size");
        }
        if (index == 0) {
            addFirst(elem);
        } else {
            Node<E> prev = head;
            //从上面的if分支就已经推断出 index > 0
            //for (int i = 1; i < index ; i++) {}
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            /*
            Node<E> node = new Node(elem);
            node.next = prev.next;
            prev.next = node;
            */
            prev.next = new Node<>(elem, prev.next);
            ++size;
        }
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
