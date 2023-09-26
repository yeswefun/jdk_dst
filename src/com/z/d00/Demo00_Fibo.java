package com.z.d00;

import com.z.util.CostUtil;

public class Demo00_Fibo {

    /*
        算法
            解决特定问题的一系列执行步骤
            不同算法解决同一问题，效率可能相差非常大

        求第n个fibonacci number
            0 1 1 2 3 5 8 13 21 34 55 89 ...
     */
    public static void main(String[] args) {
//        test();
//        System.out.println(fibonacci(32);  // 64
//        System.out.println(fibonacci2(32); // 64
//        System.out.println(fibonacci3(32); // 64

        int n = 36;
        CostUtil.check("fib1", new CostUtil.Task() {
            @Override
            public void run() {
                System.out.println(fibonacci(n));
            }
        });
        CostUtil.check("fib2", new CostUtil.Task() {
            @Override
            public void run() {
                System.out.println(fibonacci2(n));
            }
        });
        CostUtil.check("fib3", new CostUtil.Task() {
            @Override
            public void run() {
                System.out.println(fibonacci3(n));
            }
        });
    }

    private static void test() {
        for (int n = 1; n < 8; n++) {
            System.out.println(n + " --> " + fibonacci(n));
        }
    }

    /*
        求第n个fibonacci number
            0 1 1 2 3 5 8 13 21 34 55 89 ...
     */
    public static int fibonacci3(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        while (n-- > 1) { // n >= 2
            second += first;
            first = second - first;
        }
        return second;
    }

    /*
        求第n个fibonacci number
            0 1 1 2 3 5 8 13 21 34 55 89 ...

        n == 2
            0 + 1 == 1
        n == 3
            0 + 1 == 1
            1 + 1 == 2

        O(n-1) == O(n)
     */
    public static int fibonacci2(int n) { // n >= 2
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /*
        fibonacci(5)
        1 + 2 + 4 + 8 = 2^0 + 2^1 + 2^2 + 2^3 = 2^4 - 1
        即: 2^(n-1) - 1 == 0.5 * 2^n - 1
        O(2^n)
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
