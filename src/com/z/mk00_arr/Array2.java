package com.z.mk00_arr;

/*
泛型版本
 */
class Array2<E> {

    // data.length == capacity
    private E[] data;

    // element count
    private int size;

    public Array2() {
        this(8);
    }

    @SuppressWarnings("unchecked")
    public Array2(int capacity) {
        //data = new int[capacity];
        //data = new E[capacity]; //error
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //传入数组作为构造方法参数
    //...

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //添加元素 - 扩容
    //向数组末尾添加元素
    //向数组指定位置添加元素

    public void addLast(E elem) {
        add(size, elem);
    }

    public void addFirst(E elem) {
        add(0, elem);
    }

    public void add(int index, E elem) {
        if (size == data.length) {
            throw new IllegalArgumentException("size == capacity");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index < 0 || index > size");
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = elem;
        ++size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Array2: size = ")
                .append(size)
                .append(", capacity = ")
                .append(data.length);
        sb.append(", [");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    //查询元素，隐藏data
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        return data[index];
    }

    //修改元素
    public void set(int index, E elem) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        data[index] = elem;
    }

    //包含，搜索
    public boolean contains(E elem) {
        return indexOf(elem) != -1;
    }

    public int indexOf(E elem) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(elem)) { // data[i] == elem
                return i;
            }
        }
        return -1;
    }

    //删除元素 - 缩容
    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElem(E elem) {
        int index = indexOf(elem);
        if (index != -1)
            remove(index);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        --size;
        data[size] = null; // 断开引用, loitering object != memory leak
        return ret;
    }
}
