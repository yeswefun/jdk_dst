package com.z.d02_list;

/*
双向链表
 */
class DLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    /*
        私有内部类，没有必要再在属性前使用 private
     */
    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;

        public Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
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
        first == last == null, 还没有元素, size == 0
        first.prev == null
        last.next == null

        index == size
            //prev.next
            //next.prev(X)
            Node<E> prev = last;
            last = new Node<>(elem, prev, null);
            if (prev == null) { // 添加第一个元素, 2) index==0, size==0, first == last == null
                first = last;
            } else {
                prev.next = last;
            }
     */
    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        if (index == size) {
            // index == size
            //      size != 0, 最后一个位置
            //      size == 0，第一个元素
            //prev.next
            //next.prev(X)
            // 下面三句可以合成一句，last = new Node<>(elem, prev, null);
            Node<E> prev = last;
            Node<E> node = new Node<>(elem, prev, null);
            last = node;
            if (prev == null) { // 添加第一个元素, 2) index==0, size==0, first == last == null
                first = node;
            } else {
                prev.next = node;
            }
        } else { // 至少有一个元素?
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(elem, prev, next);
            next.prev = node;
            if (prev == null) { // index == 0, first
                first = node;
            } else {
                prev.next = node;
            }
        }
        ++size;
    }

    /*
        单向: node(index-1)
        双向: node(index)

        所有情况
            前驱不为 null, 后继不为 null
            前驱不为 null, 后继为 null
            前驱为 null, 后继为 null
            前驱为 null, 后继不为 null

        first == last == null, 还没有元素, size == 0
        first.prev == null
        last.next == null
     */
    @Override
    public E remove(int index) {

        rangeCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) { // index == 0
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) { // index == size-1
            last = prev;
        } else {
            next.prev = prev;
        }

        --size;
        return node.elem;
    }
//    @Override
//    public E remove(int index) {
//
//        rangeCheck(index);
//
//        Node<E> node;
//        if (index == 0) {
//            node = first;
//            node.next = null;
//            first = first.next;
//        } else if (index == size - 1) {
//            node = last;
//            node.prev = null;
//            last = last.prev;
//            last.next = null;
//        } else {
//            node = node(index);
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//            node.next = null;
//            node.prev = null;
//        }
//        size--;
//        return node.elem;
//    }

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
        双向链表查找节点比单向链表查找快
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        if (index < (size >> 1)) { // 从前往后查找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else { // 从后往前查找
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
            sb.append(node); // node.toString();
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
