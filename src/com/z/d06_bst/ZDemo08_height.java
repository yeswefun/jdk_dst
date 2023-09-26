package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo08_height {

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
        System.out.println(bst.height());

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            bst2.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst2);
        System.out.println(bst2.height());

    }

    public static void main(String[] args) {
        test1();
    }
}