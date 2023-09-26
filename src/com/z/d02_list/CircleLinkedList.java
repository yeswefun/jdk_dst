package com.z.d02_list;

/*
单向循环链表
 */
class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    /*
        私有内部类，没有必须再在属性前使用 private
     */
    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }

        @Override
        public String toString() {
            return elem + "_" + next.elem;
        }

        /*
            判断是否回收对象内存
            需要配合 System.gc() 来使用
         */
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("*** finalize *** " + elem);
        }
    }

    @Override
    public E get(int index) {
        return node(index).elem;
    }

    @Override
    public E set(int index, E elem) {
        Node<E> node = node(index);
        E oldElem = node.elem;
        node.elem = elem;
        return oldElem;
    }

    /*
        在 index == 0 添加节点，需要将 尾节点 的 next 指向 新的头节点

        index == 0
            first = new Node<>(elem, first);
            // node(index); 里面用到了 first, 所以不能这样写
            Node<E> last = (size == 0) ? node : node(size - 1);
            last.next = node;

        index == 0
            Node<E> node = new Node<>(elem, first);
            if (size == 0) {
                Node<E> last = node;
                last.next = first = node;
            } else {
                Node<E> last = node(size - 1);
                last.next = first = node;
            }
     */
    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == 0) {
            Node<E> node = new Node<>(elem, first);
            Node<E> last = (size == 0) ? node : node(size - 1);
            last.next = first = node;
        } else { // index > 0 && index is valid
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }

        ++size;
    }

    /*
        0 1 2 3
        9     4
        8 7 6 5
     */
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
        } else { // index > 0 && index is valid
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        --size;
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

        查找之前，不能修改 first
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
