package com.z.d04_queue;

class CircleQueue<E> {

    private static final int DEFAULT_CAPACITY = 6;

    private int front;

    private int size;

    private E[] elems;

    @SuppressWarnings("unchecked")
    public CircleQueue() {
        elems = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            //elems[i] = null;
            elems[circleIndex(i)] = null;
        }
        size = 0;
        front = 0;
    }

    /*
        从队尾入队
            elems[size++] = elem;

            elems[front+size] = elem;
            ++size;

            int index = (front + size) % elems.length;
            elems[index] = elem;
            ++size;
     */
    public void enQueue(E elem) {
        ensureCapacity(size + 1);
        elems[circleIndex(size)] = elem;
        ++size;
    }

    /*
        从队头出队
     */
    public E deQueue() {
        E elem = elems[front];
        elems[front] = null;
        //front = (front + 1) % elems.length;
        front = circleIndex(1);
        --size;
        return elem;
    }

    public E front() {
        return elems[front];
    }

    /*
        将当前索引转换成循环队列中的真实索引
        offset 是相对于 front 的偏移量
     */
//    private int circleIndex(int offset) {
//        return (front + offset) % elems.length;
//    }

    /*
        尽量避免使用 乘*、除/、模%、浮点数运算，效率低下

        n % m, 前提: m > 0, n >= 0, n < 2m
            n > m
                n - m
            n < m
                n
        情况一:
            n = 3
            m = 10
            n % m == 3

        情况二:
            n = 13
            m = 10
            n % m == 3

        n 与 m 的取值最大值
            m == elems.length，
                假设 elems.length == 10
            n == front + size，
                假设 front == 9，size == 9，n == 18 < 2*10 == 2m
                n == 18 % 10 == 8(index), 第 9 个

        循环队列中不会出现负数的情况，循环双端队列才会出现负数的情况
     */
    private int circleIndex(int offset) {
        offset += front;
        if (offset < elems.length) {
            return offset;
        } else { // >=, offset - elems.length == 0
            return offset - elems.length;
        }
        //return offset - (offset < elems.length ? 0 : elems.length);
    }

    /*
        动态扩容
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        int oldCapacity = elems.length;
        if (oldCapacity >= capacity)
            return;
//        1.5倍 = 1 + 0.5
        int newCapacity = oldCapacity + (oldCapacity >> 1);
//        E[] newElems = new E[newCapacity];
        E[] newElems = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            //int index = (front + i) % oldCapacity;
            int index = circleIndex(i);
            newElems[i] = elems[index];
        }
        elems = newElems;
        front = 0;
        System.out.println("old: " + oldCapacity + " , new: " + newCapacity);
    }

    /*
        注意: 此处的 i 并不是从 front 开始
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("font: ").append(front)
                .append(", capacity: ").append(elems.length)
                .append(", size: ").append(size)
                .append("[");
        for (int i = 0; i < elems.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            // 此处不是使用 circleIndex(i),
            // 即并不是相对于 front 的 offset
            sb.append(elems[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
