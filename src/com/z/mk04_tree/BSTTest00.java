package com.z.mk04_tree;

class BSTTest00 {
    /*
        满二叉树: 除了叶子节点外，都有两个子节点
     */
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
//            bst.add2(num);
        }
        /*
                    5
                  /    \
                 3      6
                / \       \
               2   4       8
         */
        // 5 3 2 4 6 8
        System.out.println("************************ pre");
        bst.preOrder();
        System.out.println("************************");
        System.out.println(bst.preOrderString());
        //System.out.println(bst);

        //bst中序遍历是有序的 - 有序树
        // 2 3 4 5 6 8
        System.out.println("************************ in");
        bst.inOrder();
        System.out.println("************************");
        System.out.println(bst.inOrderString());

        // 2 4 3 8 6 5
        System.out.println("************************ post");
        bst.postOrder();
        System.out.println("************************");
        System.out.println(bst.postOrderString());
    }
}
