package com.z.mk_iface;

interface IList<E> {

    int getSize();

    boolean isEmpty();

    //O(1)
    void addFirst(E elem);

    //O(n)
    void addLast(E elem);

    //O(n)
    void add(int index, E elem);

    //O(n)
    boolean contains(E elem); //indexOf

    //O(n)
    E get(int index);

    //O(1)
    E getFirst();

    //O(n)
    E getLast();

    //O(n)
    void set(int index, E elem);

    //O(1)
    E removeFirst();

    //O(n)
    E removeLast();

    //O(n)
    E remove(int index);
}
