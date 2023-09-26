package com.z.iface;

interface IQueue<E> {

    int size();

    boolean isEmpty();

    void enQueue(E elem);

    E deQueue();

    E front();

    void clear();
}
