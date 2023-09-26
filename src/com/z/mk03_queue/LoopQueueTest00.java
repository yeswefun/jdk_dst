package com.z.mk03_queue;

class LoopQueueTest00 {

    /*
        只有循环队列为空时, 才会有 front == tail

        只有循环队列为满时, 才会有 (rear + 1) % data.length == front
     */
    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>();
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());

        for (int i = 8; i < 16; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());

        while (!queue.isEmpty()) {
            System.out.println(queue.getFront() + "@" + queue.dequeue() + "@" + queue.getSize());
        }
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());
    }
}
