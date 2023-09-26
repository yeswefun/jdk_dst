package com.z.mk04_tree;

class BSTTest01 {
    /*
        满二叉树: 除了叶子节点外，都有两个子节点
     */
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        /*
                    5
                  /    \
                 3      6
                / \       \
               2   4       8
         */
        bst.preOrder();

        // 5 3 2 4 6 8
        System.out.println("************************ preOrderNR, stack, DFS");
        bst.preOrderNR();

        // 5 3 6 2 4 8
        System.out.println("************************ levelOrder, queue, BFS");
        bst.levelOrder();
    }
}
