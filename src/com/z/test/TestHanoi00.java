package com.z.test;

class TestHanoi00 {

    public static void main(String[] args) {
        for (int n = 1; n <= 4; n++) {
            System.out.println("----------------------------> " + n);
            char a = 'A', b = 'B', c = 'C';
            hanoi(n, a, b, c);
        }
    }

    private static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            move(n, a, c);
        } else {
            hanoi(n-1, a, c, b);
            move(n, a, c);
            hanoi(n-1, b, a, c);
        }
    }

    private static void move(int n, char a, char c) {
        System.out.println("n : " + n + ", " + a + " -> " + c);
    }
}
