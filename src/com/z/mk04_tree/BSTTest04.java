package com.z.mk04_tree;

import java.util.ArrayList;
import java.util.List;

class BSTTest04 {
    /*
        满二叉树: 除了叶子节点外，都有两个子节点

        从二分搜索树中删除最[小|大]值所在节点
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
        List<Integer> list = new ArrayList<>();
        while (!bst.isEmpty()) {
            list.add(bst.removeMin()); // 从小到大
        }
        System.out.println(list);
    }
}
