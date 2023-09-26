package com.z.mk03_queue;

class LinkedQueueTest00 {

    public static void main(String[] args) {

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());

        while (!queue.isEmpty()) {
            System.out.println(queue.getFront() + "@" + queue.dequeue() + "@" + queue.getSize());
        }
        System.out.println(queue + ", isEmpty(): " + queue.isEmpty());
    }
}
