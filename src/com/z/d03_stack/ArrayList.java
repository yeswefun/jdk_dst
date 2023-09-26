package com.z.d03_stack;

/*
时间复杂度
    get
    set
    add
    remove

ArrayList
扩容操作: 在添加元素时
缩容操作: 在删除元素时
 */
@SuppressWarnings("unchecked")
class ArrayList<E> extends AbstractList<E> {

    private E[] elems;

    //    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_CAPACITY = 4;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /*
        elems用来存放对象的数组
        所有类(除Object本身)都默认继承自Object
     */
    public ArrayList(int capacity) {
//        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elems = (E[]) new Object[capacity];
        //Type parameter 'E' cannot be instantiated directly
        //elems = new E[capacity];
    }

    /*
        O(1)
            elems[index] == *(数组首地址 + index * 4)
            数组: 随机访问速度非常快
     */
    public E get(int index) {
        rangeCheck(index);
        return elems[index];
    }

    /*
        O(1)
     */
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

    /*
        往后移动元素

        时间复杂度与size有关
            最好: O(1)
                index == size

            最坏: O(n)
                index == 0

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
                    各种复杂度的和 / 各种复杂度的多少种情况
     */
    public void add(int index, E elem) {

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

        时间复杂度与size有关
            最好: O(1)
                index == size

            最坏: O(n)
                index == 0

            平均: O(n)
                错误: index == size/2
                正确: (1 + 2 + 3 + ... + n) / n
     */
    public E remove(int index) {

        rangeCheck(index);

        E oldElem = elems[index];

//        if (size - (index + 1) >= 0) System.arraycopy(elems, index + 1, elems, index + 1 - 1, size - (index + 1));
        for (int i = index + 1; i < size; i++) {
            elems[i - 1] = elems[i];
        }
        elems[--size] = null;
        trim();
        return oldElem;
    }

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

        if (elems != null && elems.length > DEFAULT_CAPACITY) {
            elems = (E[]) new Object[DEFAULT_CAPACITY];
        }
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
        System.out.println("****** 扩大容量, old: " + oldCapacity + " , new: " + newCapacity);
    }

    /*
        扩容操作: 在添加元素时
        缩容操作: 在删除元素时
     */
    private void trim() {
        int capacity = elems.length;
        int newCapacity = capacity >> 1;
        // 有效长度大于容量大小 || 旧的容量小于默认的容量大小
        if (size >= newCapacity || capacity <= DEFAULT_CAPACITY)
            return;
        // 12 -> 6[DEFAULT_CAPACITY] -> 3 -> 1
        E[] newElems = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElems[i] = elems[i];
        }
        elems = newElems;
        System.out.println("****** 缩小容量, old: " + capacity + " , new: " + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elems[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
