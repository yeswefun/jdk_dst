package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo09_complete {

    /*
        层序遍历 - true
                7
            4      9
          2   5

        层序遍历 - false
                    7
                 4     9
              2
           1
     */
    static Integer[] data = {
            7, 4, 9, 2, 5, 8, 11,
//            3,
//            12,
//            1
    };

    static Integer[] data2 = {
            7, 4, 9, 2, 1
    };

    private static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : data2) {
            bst.add(i);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    public static void main(String[] args) {
        test1();
    }
}
