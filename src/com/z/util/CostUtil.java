package com.z.util;

public final class CostUtil {

    public interface Task {
        void run();
    }

    public static void check(String title, Task task) {
        long start = System.currentTimeMillis();
        task.run();
        System.out.println(title + " cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}
