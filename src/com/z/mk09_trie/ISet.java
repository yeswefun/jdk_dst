package com.z.mk09_trie;

interface ISet<E> {

    int getSize();

    boolean isEmpty();

    boolean contains(E elem);

    void add(E elem);

    void remove(E elem);
}
