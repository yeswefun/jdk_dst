package com.z.mk07_heap;

import java.util.Random;

class PriorityQueueTest00 {

    public static void main(String[] args) {

        int n = 32;
//        Random r = new Random();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
//            queue.enqueue(r.nextInt(64));
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
