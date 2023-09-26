package com.z.mk02_stack;

class ZBenchMark00 {

    /*
        测试使用 stack 运行 num 个 push 和 pop 操作所需要的时间，单位:秒
     */
    private static double testQueue(IStack<Integer> stack, int num) {
        long startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            stack.push(i);
        }
        for (int i = 0; i < num; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    /*
        10000
            ArrayStack:  0.0050298
            LinkedStack: 0.0030135

        100000
            ArrayStack:  0.0128288
            LinkedStack: 0.0112357

        ArrayStack 需要扩容和缩容， 而 LinkedStack 不需要这样的操作
            LinkedStack 存在 new 操作，需要查找可用内存地址

        运行多次求平均值
            jdk版本
                JVM
     */
    public static void main(String[] args) {

//        int num = 10000;
        int num = 100000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testQueue(arrayStack, num);
        System.out.println(time1);

        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        double time2 = testQueue(linkedStack, num);
        System.out.println(time2);
    }
}
