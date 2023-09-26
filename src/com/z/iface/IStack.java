package com.z.iface;

interface IStack<E> {

    int size();

    boolean isEmpty();

    void push(E elem);

    E pop();

    E top();

    void clear();
}
