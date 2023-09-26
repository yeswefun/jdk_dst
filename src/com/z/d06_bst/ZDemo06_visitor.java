package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTrees;

class ZDemo06_visitor {

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

        System.out.println("\n************************ preorder");
        bst.preorderTraversal(visitor);

        System.out.println("\n************************ inorder");
        bst.inorderTraversal(visitor);

        System.out.println("\n************************ postorder");
        bst.postorderTraversal(visitor);

        System.out.println("\n************************ levelorder");
        bst.levelorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer elem) { // void
                System.out.print(elem + " ");
                return elem == 5;
            }
        });
    }

    private static final BinarySearchTree.Visitor<Integer> visitor = new BinarySearchTree.Visitor<Integer>() {
        @Override
        public boolean visit(Integer elem) { // void
            System.out.print(elem + " ");
            return elem == 5;
        }
    };

    public static void main(String[] args) {
        test1();
    }
}