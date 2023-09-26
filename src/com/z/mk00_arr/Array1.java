package com.z.mk00_arr;

class Array1 {

    // data.length == capacity
    private int[] data;

    // element count
    private int size;

    public Array1() {
        this(8);
    }

    public Array1(int capacity) {
        data = new int[capacity];
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
//    public void addLast(int elem) {
//        if (size == data.length)
//            throw new IllegalArgumentException("size == capacity");
//        data[size] = elem;
//        ++size;
//    }
    public void addLast(int elem) {
        add(size, elem);
    }

    public void addFirst(int elem) {
        add(0, elem);
    }

    public void add(int index, int elem) {
        //确保还有空间
        if (size == data.length) {
            throw new IllegalArgumentException("size == capacity");
        }
        //确定 index 的合法性
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
        sb.append("Array: size = ")
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
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        return data[index];
    }

    //修改元素
    public void set(int index, int elem) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        data[index] = elem;
    }

    //包含，搜索
//    public boolean contains(int elem) {
//        for (int i = 0; i < size; i++) {
//            if (data[i] == elem) {
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean contains(int elem) {
        return indexOf(elem) != -1;
    }

    public int indexOf(int elem) {
        for (int i = 0; i < size; i++) {
            if (data[i] == elem) {
                return i;
            }
        }
        return -1;
    }

    //删除元素 - 缩容
    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    //用户在删除时，就已经知道 elem, 所以不需要返回被删除元素
    //删除第一个值为elem(数组中存在重复元素, removeAll)
    //返回值类型 boolean, 是否进行删除元素的操作
    public void removeElem(int elem) {
        int index = indexOf(elem);
        if (index != -1)
            remove(index);
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index < 0 || index >= size");
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        --size;
        return ret;
    }
}
