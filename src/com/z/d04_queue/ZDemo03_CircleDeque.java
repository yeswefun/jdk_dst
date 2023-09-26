package com.z.d04_queue;

class ZDemo03_CircleDeque {

    /*
        循环队列: 只要动态扩容，front 都是从 0 开始
     */
    public static void main(String[] args) {
        CircleDeque<Integer> q = new CircleDeque<>();

//头5 4 3 2 1 100 101 102 103 104 105 106 8 7 6尾
//头8 7 6 5 4 3 2 1 100 101 102 103 104 105 106 107 108 109 null null 10 9尾
//        for(inti=0;i<10;i++) {
//            queue.enQueueFront(i + 1);
//            queue.enQueueRear(i + 100);
//        }

        // 0(*) 1 2 null null null
        for (int i = 0; i < 3; i++) {
            q.enQueueRear(i);
        }
        System.out.println(q);

        // 0 1 2 5(*) 4 3
        for (int i = 3; i < 6; i++) {
            q.enQueueFront(i);
        }
        System.out.println(q);

        // capacity == 6, 0 1 2 5(*) 4 3
        // capacity == 9, 5 4 3 0 1 2 6 7 8
        // capacity == 13, 5 4 3 0 1 2 6 7 8 9 10 11 null
        for (int i = 6; i < 12; i++) {
            q.enQueueRear(i);
        }
        System.out.println(q);

        // capacity == 13, 5 4 3 0 1 2 6 7 8 9 10 11 null
        // capacity == 13, null null null null null null 6 7 8 9 10 11 null
        for (int i = 0; i < 6; i++) {
            q.deQueueFront();
        }
        System.out.println(q);

        // capacity == 13, null null null null null null 6 7 8 9 10 11 null
        // capacity == 13, -1 -2 null null null null 6 7 8 9 10 11 0
        for (int i = 0; i > -3; i--) {
            q.enQueueRear(i);
        }
        System.out.println(q);

        while (!q.isEmpty()) {
            System.out.print(q.deQueueRear() + " ");
        }
    }
}
