package com.z.mk03_queue;

class LoopQueue<E> implements IQueue<E> {

    private E[] data;
    private int front, rear;
    private int size; // 可以尝试去掉size

    public LoopQueue() {
        this(8);
    }

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1]; //浪费一个元素的空间
        size = rear = front = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    /*
        一个指向队列头front, 一个指向队列尾rear。

        在循环队列中，
            当队列为空时，有 front = rear;
            当队列为满时，有 front = rear。

        所以，为了区别这两种情况，规定循环队列最多只能有 MaxSize-1 个队列元素(也就是空一个位置)，
        当循环队列中只剩下一个空存储单元时，队列就已经满了。

        因此，
            队列判空的条件是 front = rear，
            队列判满的条件是 front = (rear+1) % MaxSize。

        综上：这个空位置，主要就是为了用来区分队空与队满情况的。

        队列满了:
            (rear + 1) % data.length == front
            size == data.length - 1
        队列为空:
            front == rear
            size == 0
     */
    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    public int getCapacity() {
        return data.length - 1; //浪费一个元素的空间
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty");
        }
        return data[front];
    }

    /*
        扩容
     */
    @Override
    public void enqueue(E elem) {
        if ((rear + 1) % data.length == front) { //+1，是因为那个浪费掉的空间不能用来存放元素呀
            resize(getCapacity() * 2); // 不是 data.length, 因为它有意识的浪费了一个元素的空间，而是 data.length-1
        }
        data[rear] = elem;
        rear = (rear + 1) % data.length;
        ++size;
    }

    /*
        缩容
     */
    @Override
    public E dequeue() {
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        --size;
        if (size < getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        System.out.println("resize: " + getCapacity() + " -> " + capacity);
        E[] newData = (E[]) new Object[capacity + 1]; //浪费一个元素的空间
        //第一种遍历方式
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        rear = size; //指向有空闲空间的下标
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoopQueue: size = ")
                .append(size)
                .append(", capacity = ")
                .append(getCapacity());
        sb.append(", front [");
        //第二种遍历方式
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != rear) {
                sb.append(", ");
            }
        }
        sb.append("] end");
        return sb.toString();
    }
}
