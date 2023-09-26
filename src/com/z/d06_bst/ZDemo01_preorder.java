package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo01_preorder {

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
        System.out.println("************************ preorder");
        bst.preorderTraversal();
    }

    public static void main(String[] args) {
        test1();
    }
}
