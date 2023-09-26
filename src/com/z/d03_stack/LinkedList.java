package com.z.d03_stack;

/*
链表的大部分接口和动态数组是一致的

List(*)
    AbstractList - 不可以实例化
        ArrayList(*)
        LinkedList(*)

单向链表
 */
class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    /*
        私有内部类，没有必要再在属性前使用 private
     */
    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }

        /*
            判断是否回收对象内存
            需要配合 System.gc() 来使用
         */
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("*** finalize ***");
        }
    }

    /*
        时间复杂度与size有关
            最好: O(1)
                index == 0

            最坏: O(n)
                index == size

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
     */
    @Override
    public E get(int index) {
        return node(index).elem;
    }

    /*
        时间复杂度与size有关
            最好: O(1)
                index == 0

            最坏: O(n)
                index == size

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
     */
    @Override
    public E set(int index, E elem) {
        Node<E> node = node(index);
        E oldElem = node.elem;
        node.elem = elem;
        return oldElem;
    }

    /*
        时间复杂度与size有关
            最好: O(1)
                index == 0

            最坏: O(n)
                index == size

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
     */
    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == 0) {
            first = new Node<>(elem, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }

        size++;
    }

    /*
        时间复杂度与size有关
            最好: O(1)
                index == 0

            最坏: O(n)
                index == size

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
     */
    @Override
    public E remove(int index) {

        rangeCheck(index);

        Node<E> node;
        if (index == 0) {
            node = first;
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        size--;

        return node.elem;
    }

    @Override
    public int indexOf(E elem) {
        Node<E> node = first;
        if (elem == null) {
            for (int i = 0; i < size; i++) {
                if (node.elem == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elem.equals(node.elem)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /*
        first引用断开，其它的节点会依次被回收
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /*
        获取index对应的节点(index是从0开始的, 所以 first 对应 index 为 0 的节点)

        0 1 2 3
            index == 0, cnt == 0
            index == 1, cnt == 1
            index == 2, cnt == 2
            ...
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /*
        Node<E> node = first;
        while (node != null) {
            // ...
            node = node.next;
        }
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node.elem);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
