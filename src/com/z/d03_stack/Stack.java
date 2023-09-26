package com.z.d03_stack;

/*
直接继承会导致 Stack 具有过多接口 - 解决:组合
 */
class Stack<E> {

    private List<E> list = new ArrayList<>();

    public void push(E elem) {
        list.add(elem);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }
}
