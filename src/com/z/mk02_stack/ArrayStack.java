package com.z.mk02_stack;

/*
数组尾部作为栈顶
 */
class ArrayStack<E> implements IStack<E> {

    private Array4<E> array;

    public ArrayStack() {
        array = new Array4<>();
    }

    public ArrayStack(int capacity) {
        array = new Array4<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E elem) {
        array.addLast(elem);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    //return array.get(array.getSize() - 1);
    @Override
    public E peek() {
        return array.getLast();
    }

    //ArrayStack特有的
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayStack: ").append("[");
        for (int i = 0; i < array.getSize(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array.get(i));
        }
        sb.append("] top");
        return sb.toString();
    }
}
