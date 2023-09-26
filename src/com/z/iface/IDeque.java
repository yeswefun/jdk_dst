package com.z.iface;

interface IDeque<E> {
    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    //从队尾入队
    E deQueueFront();

    //从队头出队
    void enQueueFront(E element);

    //从队头入队
    E deQueueRear();

    //从队尾出队
    E front();

    //获取队列的头元素
    void enQueueRear(E element);

    //获取队列的尾元素
    E rear();

    void clear();
}
