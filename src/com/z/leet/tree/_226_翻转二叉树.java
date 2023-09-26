package com.z.leet.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.cn/problems/invert-binary-tree/

给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]

示例 2：
输入：root = [2,1,3]
输出：[2,3,1]

示例 3：
输入：root = []
输出：[]

提示：

树中节点数目范围在 [0, 100] 内
-100 <= Node.val <= 100
 */
class _226_翻转二叉树 {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return root;

        invertTree2(root.left);
        invertTree2(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    public TreeNode invertTree4(TreeNode root) {
        if (root == null)
            return root;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
    }

    /*
        注意: 中序遍历
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null)
            return root;

        invertTree3(root.left);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //invertTree3(root.right);
        invertTree3(root.left);

        return root;
    }
}
