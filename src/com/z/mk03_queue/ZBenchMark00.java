package com.z.mk03_queue;

class ZBenchMark00 {

    /*
        测试使用 queue 运行 num 个 enqueue 和 dequeue 操作所需要的时间，单位:秒
     */
    private static double testQueue(IQueue<Integer> queue, int num) {
        long startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < num; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    /*
        10000
            arrayQueue: 0.0499739
            loopQueue:  0.0048258

        100000
            arrayQueue: 3.1078831
            loopQueue:  0.0109162

        ArrayQueue 与 LoopQueue 出队时的复杂度
            ArrayQueue
                O(n)
            LoopQueue
                O(1)

            ArrayQueue 在 出栈 时，需要将栈中所有元素往前移动，
            而 LoopQueue 在 出栈 时不需要这样的操作

        运行多次求平均值
            jdk版本
                JVM
     */
    public static void main(String[] args) {

//        int num = 10000;
        int num = 100000;

        //当数据量比较大时，可以注释掉
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, num);
        System.out.println(time1);

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, num);
        System.out.println(time2);

        /*
            3.1483863
            0.011069
            0.0126739
         */
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        double time3 = testQueue(linkedQueue, num);
        System.out.println(time3);
    }
}
