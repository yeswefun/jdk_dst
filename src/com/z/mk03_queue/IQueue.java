package com.z.mk03_queue;

interface IQueue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E elem);

    E dequeue();

    E getFront();
}
