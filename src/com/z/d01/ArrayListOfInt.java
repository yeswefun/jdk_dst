package com.z.d01;

import java.util.Arrays;

public class ArrayListOfInt {

    private int size;
    private int[] elems;

//    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayListOfInt() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListOfInt(int capacity) {
//        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elems = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        rangeCheck(index);
        return elems[index];
    }

    public int set(int index, int elem) {
        rangeCheck(index);
        int oldElem = elems[index];
        elems[index] = elem;
        return oldElem;
    }

    public int indexOf(int elem) {
        for (int i = 0; i < size; i++) {
            if (elems[i] == elem) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(int elem) {
        return indexOf(elem) != ELEMENT_NOT_FOUND;
    }

    public void add(int elem) {
//        elems[size++] = elem;
        add(size, elem);
    }

    /*
        往后移动元素
     */
    public void add(int index, int elem) {
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException("index: " + index + " , but size: " + size);
//        }
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

//        System.arraycopy(elems, index, elems, index + 1, size - index);
        for (int i = size; i > index; i--) {
            elems[i] = elems[i - 1];
        }
        elems[index] = elem;
        size++;
    }

    /*
        往前移动元素
     */
    public int remove(int index) {

        rangeCheck(index);

        int oldElem = elems[index];

//        if (size - (index + 1) >= 0) System.arraycopy(elems, index + 1, elems, index + 1 - 1, size - (index + 1));
        for (int i = index + 1; i < size; i++) {
            elems[i - 1] = elems[i];
        }
        size--;
        return oldElem;
    }

    /*
        值类型可以这样清空，但引用类型，尽量尽早断开对象引用
     */
    public void clear() {
        size = 0;
    }

    /*
        扩容
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elems.length;
        if (oldCapacity >= capacity)
            return;
//        1.5倍 = 1 + 0.5
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        int[] newElems = new int[newCapacity];
//        if (size >= 0) System.arraycopy(elems, 0, newElems, 0, size);
        for (int i = 0; i < size; i++) {
            newElems[i] = elems[i];
        }
        elems = newElems;
        System.out.println("old: " + oldCapacity + " , new: " + newCapacity);
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + " , but size: " + size);
    }

    /*
        remove, get, set
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    /*
        add可以在尾部插入元素，最多就是动态扩容
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", elems=" + Arrays.toString(elems) +
                '}';
    }
}
