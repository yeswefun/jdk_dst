package com.z.d03_stack;

/*
    抽象类 - 公共逻辑抽取，复用
    接口 - 标准

    接口方法默认修饰符是 public
    接口字段默认修饰符是 public static final
 */
interface List<E> {

    int ELEMENT_NOT_FOUND = -1;

    int size();

    boolean isEmpty();

    E get(int index);

    E set(int index, E elem);

    int indexOf(E elem);

    boolean contains(E elem);

    void add(E elem);

    void add(int index, E elem);

    E remove(int index);

    //E remove(E elem);

    void clear();
}
