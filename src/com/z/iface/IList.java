package com.z.iface;

interface IList<E> {

    int size();

    boolean isEmpty();

    E get(int index);

    E set(int index, E elem);

    int indexOf(E elem);

    boolean contains(E elem);

    void add(E elem);

    void add(int index, E elem);

    E remove(int index);

    void clear();
}
