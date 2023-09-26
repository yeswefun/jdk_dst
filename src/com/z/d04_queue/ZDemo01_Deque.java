package com.z.d04_queue;

class ZDemo01_Deque {

    public static void main(String[] args) {
        Deque<Integer> queue = new Deque<>();
        queue.enQueueRear(11);
        queue.enQueueRear(22);
        queue.enQueueFront(33);
        queue.enQueueFront(44);
        while (!queue.isEmpty()) {
            System.out.println(queue.front() + " @ " + queue.deQueueFront());
        }
    }
}
