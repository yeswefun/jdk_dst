package com.z.mk_iface;

interface IStack<E> {

    int getSize();

    boolean isEmpty();

    void push(E elem);

    E pop();

    //查看栈顶元素
    E peek();
}
