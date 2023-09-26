package com.z.d02_list;

/*
双向循环链表
 */
class DCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    /*
        私有内部类，没有必须再在属性前使用 private
     */
    private static class Node<E> {
        E elem;
        Node<E> next;
        Node<E> prev;

        public Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev == null) {
                sb.append("null");
            } else {
                sb.append(prev.elem);
            }
            sb.append("_").append(elem).append("_");
            if (next == null) {
                sb.append("null");
            } else {
                sb.append(next.elem);
            }
            return sb.toString();
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
        first.prev == null
        last.next == null

        first
        first.prev
        last
        last.next

        // version-1
            Node<E> oldLast = last;
//            last = new Node<>(elem, oldLast, null);
            last = new Node<>(elem, oldLast, first);
            if (oldLast == null) { // 添加第一个元素, 2) index==0, size==0
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }

        // version-2
            Node<E> prev = last;
            Node<E> node = new Node<>(elem, prev, first);
            last = node;
            if (prev == null) { // 添加第一个元素, 2) index==0, size==0
                first = node;
                first.next = first;
                first.prev = first;
            } else {
                prev.next = node;
                first.prev = node;
            }

        not work
            first.prev = first.next = first = node;
     */
    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == size) { // 1) last; 2) index==0, size==0
            Node<E> prev = last;
            Node<E> node = new Node<>(elem, prev, first);
            last = node;
            if (prev == null) { // 添加第一个元素, 2) index==0, size==0
                first = node;
                first.prev = first.next = node;
            } else {
                first.prev = prev.next = node;
            }
        } else { // 至少两个元素以上
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(elem, prev, next);
            next.prev = node;
            prev.next = node;
            if (index == 0) { // index == 0, next == first, first
                first = node;
            }
        }
        ++size;
    }

    /*
        单向: node(index-1)
        双向: node(index)
     */
    @Override
    public E remove(int index) {

        rangeCheck(index);

        Node<E> node;
        if (size == 1) {
            node = first;
            first = last = null;
        } else { // 至少两个的元素
            node = node(index);
            Node<E> next = node.next;
            Node<E> prev = node.prev;

            prev.next = next;
            next.prev = prev;

            if (index == 0) { // node == first
                first = next;
                first.prev = last;
            }

            if (index == size - 1) { // node == last
                last = prev;
                last.next = first;
            }
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
        first，last引用断开，其它的节点会依次被回收
            其它节点没有被 gc root 对象所引用

        gc root 对象
            1. 被栈指针指向的对象
                循环引用也会回收
     */
    @Override
    public void clear() {
        size = 0;
        first = last = null;
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

        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
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
            //sb.append(node.elem);
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
