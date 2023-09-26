package com.z.mk_iface;

interface IQueue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E elem);

    E dequeue();

    E getFront();

    E getBack();
}
