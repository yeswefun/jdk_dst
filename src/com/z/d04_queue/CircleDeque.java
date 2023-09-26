package com.z.d04_queue;

/*
循环双端队列
    双端队列 + 循环特性
 */
class CircleDeque<E> {

    private static final int DEFAULT_CAPACITY = 6;

    //没有必要添加rear，因为可以通过 front 和 size 和 elems.length
    //private int rear;

    private int front;

    private int size;

    private E[] elems;

    @SuppressWarnings("unchecked")
    public CircleDeque() {
        elems = (E[]) new Object[DEFAULT_CAPACITY];
    }


    // 元素的数量
    public int size() {
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elems[circleIndex(i)] = null;
        }
        size = 0;
        front = 0;
    }

    //获取队列的头元素
    public E front() {
        return elems[front];
    }

    //获取队列的尾元素
    public E rear() {
        return elems[circleIndex(size - 1)];
    }

    //从队尾入队
    public void enQueueRear(E elem) {
        ensureCapacity(size + 1);
        elems[circleIndex(size)] = elem;
        ++size;
    }

    //从队头出队
    public E deQueueFront() {
        E elem = elems[front];
        elems[front] = null;
        front = circleIndex(1);
        --size;
        return elem;
    }

    /*
        从队头入队
        //elems[front前面的位置] = elem;
        //elems[front-1] = elem; // front == 0，negative
     */
    public void enQueueFront(E elem) {
        ensureCapacity(size + 1);
        front = circleIndex(-1);
        elems[front] = elem;
        ++size;
    }

    // 从队尾出队
    public E deQueueRear() {
        int index = circleIndex(size-1);
        E elem = elems[index];
        elems[index] = null;
        --size;
        return elem;
    }

    /*
        将当前索引转换成循环队列中的真实索引
        offset 是相对于 front 的偏移量

        //没有考虑(front+offset)为负数的情况
        //return (front + offset) % elems.length;
     */
    private int circleIndex(int offset) {
        //考虑(front+offset)为负数的情况
        offset += front;
        if (offset < 0) {
            offset += elems.length;
        }
        //return offset % elems.length;
        //对上面 return 的优化
        if (offset < elems.length) {
            return offset;
        } else {
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
