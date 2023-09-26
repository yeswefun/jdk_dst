package com.z.mk_iface;

interface ISet<E> {

    int getSize();

    boolean isEmpty();

    boolean contains(E elem);

    void add(E elem);

    void remove(E elem);
}
