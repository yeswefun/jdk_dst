package com.z.mk_iface;

interface IArray<E> {

    int getCapacity();

    int getSize();

    boolean isEmpty();

    void addLast(E elem);

    void addFirst(E elem);

    void add(int index, E elem);

    E getFirst();

    E getLast();

    E get(int index);

    void set(int index, E elem);

    boolean contains(E elem);

    int indexOf(E elem);

    E removeFirst();

    E removeLast();

    void removeElem(E elem);

    E remove(int index);
}
