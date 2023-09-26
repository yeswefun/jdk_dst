package com.z.d03_stack;

/*
直接继承会导致 Stack 具有过多接口 - 解决:组合
    Stack<Integer> stack = new Stack<>();
    Integer integer = stack.get(0);
 */
class Stack2<E> extends ArrayList<E> {

    public void push(E elem) {
        add(elem);
    }

    public E pop() {
        return remove(size - 1);
    }

    public E top() {
        return get(size - 1);
    }

//    public int size() {
//        return 0;
//    }
//
//    public boolean isEmpty() {
//        return true;
//    }
}
