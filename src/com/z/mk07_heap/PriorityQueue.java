package com.z.mk07_heap;

/*
优先级高的先出队
    一个比较过程
 */
class PriorityQueue<E extends Comparable<E>> implements IQueue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E elem) {
        maxHeap.add(elem);
    }

    /*
        优先队列: 出队时，队首为优先级最高的元素
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}
