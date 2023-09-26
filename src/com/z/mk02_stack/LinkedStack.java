package com.z.mk02_stack;

/*
链表头部作为栈顶
 */
class LinkedStack<E> implements IStack<E> {

    private LinkedList3<E> list;

    public LinkedStack() {
        list = new LinkedList3<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E elem) {
        list.addFirst(elem);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedStack: top ");
        sb.append(list);
        return sb.toString();
    }
}
