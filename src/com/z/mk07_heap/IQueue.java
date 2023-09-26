package com.z.mk07_heap;

interface IQueue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E elem);

    E dequeue();

    E getFront();
}
