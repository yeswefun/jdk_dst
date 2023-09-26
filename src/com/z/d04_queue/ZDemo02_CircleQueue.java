package com.z.d04_queue;

class ZDemo02_CircleQueue {

    public static void main(String[] args) {
        CircleQueue<Integer> q = new CircleQueue<>();

        // 0(*) 1 2 3 4 5
        for (int i = 0; i < 6; i++) {
            q.enQueue(i);
        }
        System.out.println(q);

        // null null null 3(*) 4 5
        for (int i = 0; i < 3; i++) {
            q.deQueue();
        }
        System.out.println(q);

        // capacity == 6, 10 11 12 3(*) 4 5
        // capacity == 9, 3(*) 4 5 10 11 12 13 14 15
        // capacity == 13, 3(*) 4 5 10 11 12 13 14 15 16 17 null null
        for (int i = 10; i < 18; i++) { // 16
            q.enQueue(i);
        }
        System.out.println(q);

        while (!q.isEmpty()) {
            System.out.print(q.deQueue() + " ");
        }
    }
}
