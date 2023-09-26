package com.z.d07_avl;


import com.z.d06_bst.printer.BinaryTrees;

class ZDemo00_avl {

    static Integer[] data = {
            7, 4, 9, 2, 5, 8, 11, 3,
            12, 1
    };

    private static AVLTree<Integer> generateBst() {
        AVLTree<Integer> bst = new AVLTree<>();
        for (Integer i : data) {
            bst.add(i);
        }
        BinaryTrees.println(bst);
        return bst;
    }

    public static void main(String[] args) {
        generateBst();
    }
}
