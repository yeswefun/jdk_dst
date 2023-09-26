package com.z.mk03_queue;

class ArrayQueue<E> implements IQueue<E> {

    private Array4<E> array;

    public ArrayQueue() {
        array = new Array4<>();
    }

    public ArrayQueue(int capacity) {
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
    public void enqueue(E elem) {
        array.addLast(elem);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayQueue: ").append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array.get(i));
        }
        sb.append("] back");
        return sb.toString();
    }
}
