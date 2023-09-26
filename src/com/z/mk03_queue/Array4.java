package com.z.mk03_queue;

/*
泛型版本
动态数组
 */
class Array4<E> {

    // data.length == capacity
    private E[] data;

    // element count
    private int size;

    public Array4() {
        this(8);
    }

    @SuppressWarnings("unchecked")
    public Array4(int capacity) {
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

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index < 0 || index > size");
        }

        if (size == data.length) {
            resize(data.length * 2);
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
        sb.append("Array4: size = ")
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

    //查询元素，修改元素
    //获取元素，隐藏data
    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        return data[index];
    }

    public void set(int index, E elem) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        data[index] = elem;
    }

    public boolean contains(E elem) {
        return indexOf(elem) != -1;
    }

    public int indexOf(E elem) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(elem)) {
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

        //临界位置波动-fix
        if (size < data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /*
        扩容
            data.length + data.length >> 2

        缩容
            resize: 8 -> 4
            1@1@1
            resize: 4 -> 2
            0@0@0
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        System.out.println("resize: " + data.length + " -> " + capacity);
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
