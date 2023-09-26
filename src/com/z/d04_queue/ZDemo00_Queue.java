package com.z.d04_queue;

class ZDemo00_Queue {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        while (!queue.isEmpty()) {
            System.out.println(queue.front() + " @ " + queue.deQueue());
        }

        java.util.Queue<String> q;
        java.util.LinkedList<String> l;
    }
}
