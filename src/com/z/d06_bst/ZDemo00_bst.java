package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

import java.util.Comparator;

/*
Comparable
Comparator
 */
class ZDemo00_bst {

    private static class TargetComparator implements Comparator<Target> {
        @Override
        public int compare(Target o1, Target o2) {
            return o1.getId() - o2.getId();
        }
    }

    private static class TargetComparator2 implements Comparator<Target> {
        @Override
        public int compare(Target o1, Target o2) {
            return o2.getId() - o1.getId();
        }
    }

    static Integer[] data = {
            7, 4, 9, 2, 5, 8, 11, 3,
            12, 1
    };

    private static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : data) {
            bst.add(i);
        }
        BinaryTrees.println(bst);
        BinaryTrees.println(bst, BinaryTrees.PrintStyle.INORDER);
    }
    private static void test2() {
        // id大的在右边，new TargetComparator()
        BinarySearchTree<Target> bst2 = new BinarySearchTree<>();
        for (Integer i : data) {
            bst2.add(new Target(i));
        }
        BinaryTrees.println(bst2);

        // id大的在左边
        BinarySearchTree<Target> bst3 = new BinarySearchTree<>(new TargetComparator2());
        for (Integer i : data) {
            bst3.add(new Target(i));
        }
        BinaryTrees.println(bst3);

        BinarySearchTree<Target> bst4 = new BinarySearchTree<>(new Comparator<Target>() {
            @Override
            public int compare(Target o1, Target o2) {
                return o1.getId() - o2.getId();
            }
        });
        for (Integer i : data) {
            bst4.add(new Target(i));
        }
        BinaryTrees.println(bst4);
    }
    private static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst);
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
/*
        // id大的在右边
        BinarySearchTree<Target> bst2 = new BinarySearchTree<>(new TargetComparator());
        for (Integer i : data) {
            bst2.add(new Target(i));
        }

        // id大的在左边
        BinarySearchTree<Target> bst3 = new BinarySearchTree<>(new TargetComparator2());
        for (Integer i : data) {
            bst2.add(new Target(i));
        }
 */

// 如果使用 Comparable 的话，如果类已经定义好了，那么比较规则就定了 - 不灵活
/*
        // id大的在右边
        BinarySearchTree<Target> bst2 = new BinarySearchTree<>();
        for (Integer i : data) {
            bst2.add(new Target(i));
        }

        // id大的在左边
        BinarySearchTree<Target> bst3 = new BinarySearchTree<>();
        for (Integer i : data) {
            bst2.add(new Target(i));
        }
 */

/*
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : data) {
            bst.add(i);
        }
 */
    }
}
