package com.z.d01;

@SuppressWarnings("unchecked")
class ArrayList<E> {

    private int size;
    private E[] elems;

    //    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /*
        elems用来存放对象的数组
        所有类(除Object本身)都默认继承自Object

        引用类型都是栈上指针指向堆内存地址
            因为指针的大小在同一机器大小固定，所以可以使用Object先分配固定大小的栈内存
     */
    public ArrayList(int capacity) {
//        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elems = (E[]) new Object[capacity];
        //Type parameter 'E' cannot be instantiated directly
        //elems = new E[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        rangeCheck(index);
        return elems[index];
    }

    public E set(int index, E elem) {
        rangeCheck(index);
        E oldElem = elems[index];
        elems[index] = elem;
        return oldElem;
    }

    /*
        elems[i] == elem
            对象内存地址
     */
    public int indexOf(E elem) {
        if (elem == null) {
            for (int i = 0; i < size; i++) {
                if (elems[i] == null) { // 第一个为值为null的序号
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elem.equals(elems[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(E elem) {
        return indexOf(elem) != ELEMENT_NOT_FOUND;
    }

    public void add(E elem) {
        //elems[size++] = elem;
        add(size, elem);
    }

    /*
        往后移动元素
     */
    public void add(int index, E elem) {
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
    public E remove(int index) {

        rangeCheck(index);

        E oldElem = elems[index];
//        if (size - (index + 1) >= 0) System.arraycopy(elems, index + 1, elems, index + 1 - 1, size - (index + 1));
        for (int i = index + 1; i < size; i++) {
            elems[i - 1] = elems[i];
        }
        elems[--size] = null;
        return oldElem;
    }

//    public void remove(E elem) {
//        remove(indexOf(elem));
//    }

    /*
        整个数组的引用置为null
            elems = null; // 如果这样做，等一下还是需要分配栈内存

        调用方法耗时，而且remove前面的元素时需要移动元素
        for (int i = 0; i < size; i++) {
            remove(i);
        }

        每个元素的引用置为null(我们采用这种方式)
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elems[i] = null;
        }
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
//        E[] newElems = new E[newCapacity];
        E[] newElems = (E[]) new Object[newCapacity];
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
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(", [");
        for (int i = 0; i < size; i++) { // 是size，而不是 elems.length
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elems[i]);
        }
        sb.append("]");
        return sb.toString();
    }

//    @Override
//    public String toString() {
//        return "ArrayList{" +
//                "size=" + size +
//                ", elems=" + Arrays.toString(elems) +
//                '}';
//    }
}
