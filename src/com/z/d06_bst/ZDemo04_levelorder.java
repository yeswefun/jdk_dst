package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo04_levelorder {

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
        System.out.println("************************ levelorder");
        bst.levelorderTraversal();
    }

    public static void main(String[] args) {
        test1();
    }
}