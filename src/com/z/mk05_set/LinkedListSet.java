package com.z.mk05_set;

/*
因为set底层是使用 LinkList 来实现的，
所以 元素 不需要具备可比性
 */
class LinkedListSet<E> implements ISet<E> {

    private LinkedList3<E> list;

    public LinkedListSet() {
        list = new LinkedList3<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E elem) {
        return list.contains(elem);
    }

    /*
        避免添加重复元素
     */
    @Override
    public void add(E elem) {
        if (list.contains(elem))
            return;
        list.addFirst(elem);
    }

    @Override
    public void remove(E elem) {
        list.removeElement(elem);
    }
}
