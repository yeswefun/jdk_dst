package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo11_remove {

    static Integer[] data = {
            7, 4, 9, 2, 5, 8, 11, 3,
            12, 1
    };

    private static BinarySearchTree<Integer> generateBst() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : data) {
            bst.add(i);
        }
        BinaryTrees.println(bst);
        return bst;
    }

    private static void test00_degree_0() {
        BinarySearchTree<Integer> bst = generateBst();

        bst.remove(1);
        BinaryTrees.println(bst);

        bst.remove(3);
        BinaryTrees.println(bst);

        bst.remove(12);
        BinaryTrees.println(bst);
    }

    private static void test01_degree_0() {
        BinarySearchTree<Integer> bst = generateBst();

        bst.remove(5);
        BinaryTrees.println(bst);

        bst.remove(8);
        BinaryTrees.println(bst);
    }

    private static void test02_degree_1() {
        BinarySearchTree<Integer> bst = generateBst();

        bst.remove(11);
        BinaryTrees.println(bst);

        bst.remove(9);
        BinaryTrees.println(bst);
    }

    private static void test03_root() {
        BinarySearchTree<Integer> bst = generateBst();

        bst.remove(7);
        BinaryTrees.println(bst);
    }

    public static void main(String[] args) {
//        test00_degree_0();
//        test01_degree_0();
//        test02_degree_1();
        test03_root();
    }
}
