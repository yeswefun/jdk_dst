package com.z.mk05_set;

interface ISet<E> {

    int getSize();

    boolean isEmpty();

    boolean contains(E elem);

    void add(E elem);

    void remove(E elem);
}
