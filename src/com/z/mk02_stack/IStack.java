package com.z.mk02_stack;

interface IStack<E> {

    int getSize();

    boolean isEmpty();

    void push(E elem);

    E pop();

    //查看栈顶元素
    E peek();
}
