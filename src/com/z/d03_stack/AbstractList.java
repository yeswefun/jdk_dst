package com.z.d03_stack;

abstract class AbstractList<E> implements List<E> {

    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
        调用子类实现的 indexOf 方法
     */
    public boolean contains(E elem) {
        return indexOf(elem) != ELEMENT_NOT_FOUND;
    }

    /*
        调用子类实现的 add 方法

        复杂度
            最好: O(1)
            最坏: O(n)
                扩容
            平均: O(1)
                1+1+1+...+1+n(扩容) / n
            均摊: O(1)
                容量: 4
                1 1 1 1 4
                2 2 2 2
     */
    public void add(E elem) {
        add(size, elem);
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + " , but size: " + size);
    }

    /*
        remove, get, set
     */
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    /*
        add可以在尾部插入元素，最多就是动态扩容
     */
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
