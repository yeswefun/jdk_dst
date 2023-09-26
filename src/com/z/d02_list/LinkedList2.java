package com.z.d02_list;

/*
链表的大部分接口和动态数组是一致的

List(*)
    AbstractList - 不可以实例化
        ArrayList(*)
        LinkedList(*)

注: 增加一个虚拟头节点
    node(int index)
    add()
    remove()
    ...

    在没有虚拟头节点之前，添加是需要找到指定index之前的元素
    但是此时index为0之前的元素不存在，所以要进行区分index是否为0
    但加上虚拟头节点之后，将index为0的情况与其它情况统一了

带虚拟头节点的单向链表
 */
class LinkedList2<E> extends AbstractList<E> {

    private Node<E> first;

    public LinkedList2() {
        first = new Node<>(null, null);
    }

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

     */
    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.elem;
    }

    @Override
    public E set(int index, E elem) {
        Node<E> node = node(index);
        E oldElem = node.elem;
        node.elem = elem;
        return oldElem;
    }

    /*
        index == 0
     */
    @Override
    public void add(int index, E elem) {

        rangeCheckForAdd(index);

        /*
        if (index == 0) {
            first = new Node<>(elem, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(elem, prev.next);
        }
         */
        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<>(elem, prev.next);
        size++;
    }

    /*
        index == 0
     */
    @Override
    public E remove(int index) {

        rangeCheck(index);

//        Node<E> node;
//        if (index == 0) {
//            node = first;
//            first = first.next;
//        } else {
//            Node<E> prev = node(index - 1);
//            node = prev.next;
//            prev.next = node.next;
//        }
        Node<E> prev = index == 0 ? first : node(index - 1);
        Node<E> node = prev.next;
        prev.next = node.next;
        size--;
        return node.elem;
    }

    @Override
    public int indexOf(E elem) {
        Node<E> node = first.next;
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
        first.next = null;
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

        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        Node<E> node = first.next;
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
