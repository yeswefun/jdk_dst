package com.z.d00;

public class ZDemo01_O {

    public static void test00(int n) {
        // 1
        if (n > 10) {
            System.out.println("n < 10");
        } else if (n > 5) {
            System.out.println("5 < n <= 10");
        } else {
            System.out.println("n <= 5");
        }
        // 1 + 4 + 4 + 4
        for (int i = 0; i < 4; i++) {
            System.out.println("test");
        }
        // 14
        // time: O(1)
        // space: O(1)
    }

    /*
        1 + n + n + n
        3n + 1
        time: O(n)
        space: O(1)
     */
    public static void test02(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
    }

    /*
        1 + n + n + n*(1 + n + n + n)
        1 + 2n + n + 3n^2
        3n^2 + 3n + 1
        O(n^2)
     */
    public static void test03(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    /*
        1 + n + n + n*(1+15+15+15)
        1 + 2n + 46n
        48n + 1
        O(n)
     */
    public static void test04(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }
    }

    /*
        n == 8 = 2^[3]
            4
            2
            1
            0
        log2(8) = 3

        n == 16 = 2^[4]
        log2(16) = 4

        执行次数 = log2(n)
            判断n能除以2，除多少次

        对数阶一般省略底数
            换底公式:
                loga(b) = logc(b) / logc(a)

            log2(n) = log2(9) * log9(n)
                log2(9)是常数，而常数项是可以忽略的
                所以 log2(n), log9(n) 统称为 logn

        O(logn)
     */
    public static void test05(int n) {
        while ((n = n / 2) > 0) {
            System.out.println("test");
        }
    }

    /*
        log5(n)
            判断n能除以5，除多少次
        O(logn)
     */
    public static void test06(int n) {
        while ((n = n / 5) > 0) {
            System.out.println("test");
        }
    }

    /*
        1 + log2(n) + log2(n) + log2(n) * (3n + 1)
        1 + 2log2(n) + log2(n)*(3n+1)
        1 + 3log2(n) + 3nlog2(n)
        O(logn + nlogn) == O(nlogn)
            logn 的阶数比 nlogn 的阶数小，所以 logn 去掉

        O(n + logn) == O(n)
     */
    public static void test07(int n) {
        /*
            i += i
            i = 2*i
                2*i < n
                i < n/2

            log2(n)
                1*2*2*2*...*2 < n
         */
        for (int i = 1; i < n; i += i) {
            /*
                1 + n + n + n
                3n + 1
             */
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    /*
        space: O(n)
     */
    public static void test10(int n) {
        int a = 10;
        int b = 20;
        int c = a + b;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + c);
        }
    }
}
