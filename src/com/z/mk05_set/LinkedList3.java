package com.z.mk05_set;

/*
带虚拟头节点
 */
class LinkedList3<E> {

    private Node<E> dummyHead;
    private int size;

    public LinkedList3() {
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
        //target: prev
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //index == 0
        //index != 0
        prev.next = new Node<>(elem, prev.next);
        ++size;
    }

    public E get(int index) {
        // index != size
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("error: index < 0 || index >= size");
        }
        //target: index
        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.elem;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E elem) {
        // index != size
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("error: index < 0 || index >= size");
        }
        //target: index
        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.elem = elem;
    }

    public boolean contains(E elem) {
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            if (cur.elem.equals(elem)) {
                return true;
            }
            cur = cur.next;
        }
        /*
        for (int i = 0; i < size; i++) {
            if (cur.elem.equals(elem)) {
                return true;
            }
        }
         */
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList3: ");
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.elem).append(" -> ");
            cur = cur.next;
        }
        sb.append("NUll");
        return sb.toString();
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E remove(int index) {
        // index != size
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("error: index < 0 || index >= size");
        }
        //target: prev
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<E> node = prev.next;
        prev.next = node.next;
        node.next = null;
        --size;
        return node.elem;
    }

    /*
        默认删除第一个与elem相等的节点
     */
    public void removeElement(E elem) {
        Node<E> prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.elem.equals(elem)) {
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {
            Node<E> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            --size;
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
