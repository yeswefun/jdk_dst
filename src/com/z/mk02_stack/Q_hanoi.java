package com.z.mk02_stack;

class Q_hanoi {

    private static void move(int n, char a, char b) {
        System.out.println("把第" + n + "个盘子从" + a + "移到" + b);
    }

    /**
     * @param n 一共需要移动的盘子
     * @param a 盘子移动的起始柱子
     * @param b 借助的柱子
     * @param c 盘子需要移动到的目标柱子
     */
    private static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            //只有一个盘子的时候，就直接从A移到C
            move(n, a, c);
        } else {
            //三步曲，注意观察，a,b,c三个的位置变化
            //1.把 n-1 个盘子看成一个整体，借助 C 从 A 移动到 B
            hanoi(n - 1, a, c, b);
            //2.把第 n 个盘子从 A 移动到 C
            move(n, a, c);
            //3.再把 n-1 盘子整体，借助 A 从 B 移动到 C
            hanoi(n - 1, b, a, c);
        }
    }

    /*
        汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。
        大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
        大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
        并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘
     */
    public static void main(String[] args) {
        for (int n = 1; n <= 4; n++) {
            System.out.println("----------------------------> " + n);
            char a = 'A', b = 'B', c = 'C';
            hanoi(n, a, b, c);
        }
    }
}
