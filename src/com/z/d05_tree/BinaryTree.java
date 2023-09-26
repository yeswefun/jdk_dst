package com.z.d05_tree;

import com.z.d06_bst.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;

    protected Node<E> root;

    protected static class Node<E> {
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E elem, Node<E> parent) {
            this.elem = elem;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
        利用 java 语言本身的特性 - gc
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /*
        节点 Node 对外不可见，可见的是 elem
     */
    public static abstract class Visitor<E> {
        boolean stop;
        abstract boolean visit(E elem);
    }

    /*
        前序
     */
    public void preorderTraversal(BST.Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor.visit(node.elem))
            return;
        //System.out.print(node.elem + " ");
//        if (visitor.visit(node.elem))
//            return;
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /*
        中序
     */
    public void inorderTraversal(BST.Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor.stop)
            return;
        inorderTraversal(node.left, visitor);
        //System.out.print(node.elem + " ");
        if (visitor.stop)
            return;
        visitor.stop = visitor.visit(node.elem);
        inorderTraversal(node.right, visitor);
    }

    /*
        后序
     */
    public void postorderTraversal(BST.Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, BST.Visitor<E> visitor) {
        //阻止递归进来
        if (node == null || visitor.stop)
            return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor); // visitor.stop == true
        //System.out.print(node.elem + " ");
        //阻止继续打印
        if (visitor.stop)
            return;
        visitor.stop = visitor.visit(node.elem);
    }

    /*
        层序
     */
    public void levelorderTraversal(BST.Visitor<E> visitor) {
        if (root == null || visitor == null)
            return;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) { // 一开始肯定不为空
            Node<E> node = q.poll();
            //System.out.print(node.elem + " "); // 访问逻辑
            if (visitor.visit(node.elem))
                return;
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    /*
        后继节点
            中序遍历时目标节点的后一个节点
     */
    protected Node<E> successor(Node<E> node) {

        if (node == null)
            return node;

        /*
            后继节点在右子树(node.right.left.left.left...)
                4(-)
                    8
                  7   10
                5(+)
         */
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        /*

         */
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.left
        return node.parent;
    }

    /*
        前驱节点
            中序遍历时目标节点的前一个节点
     */
    protected Node<E> predecessor(Node<E> node) {

        if (node == null)
            return node;

        /*
            前驱节点在左子树(node.left.right.right.right...)
                    8(-)
                 4
               2    6
                       7(+)
         */
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        /*
            从父节点，祖父节点中查找前驱节点
                    8(+)
                        13
                     10
                   9(-) 12
         */
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /*
        是否是一棵完全二叉树
     */
    public boolean isComplete() {
        if (root == null)
            return false;
        boolean leaf = false;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            if (leaf && !node.isLeaf())
                return false;
            if (node.left != null) {
                q.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null
                return false;
            }
            if (node.right != null) {
                q.offer(node.right);
            } else {
                // 只要 node.right == null，剩下的节点只能是叶子节点
                // node.left == null && node.right == null
                // node.left != null && node.right == null
                leaf = true;
            }
        }
        return true;
    }

    /*
        是不是一棵完全二叉树
                   16
             10         20
          8   [11]  [18]  [21]

                    7
                 4     9
              2
           1
     */
    public boolean isComplete2() {
        if (root == null)
            return false;

        boolean leaf = false;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                //node.left != null && node.right != null
                q.offer(node.left);
                q.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                // node.left != null && node.right == null
                // node.left == null && node.right == null
                //后面遍历的节点都必须是叶子节点
                leaf = true;
                if (node.left != null) {
                    q.offer(node.left);
                }
            }
        }
        return true;
    }

    /*
        非递归版本
     */
    public int height() {
        if (root == null)
            return 0;

        // 树的高度
        int height = 0;
        // 存储每一层的元素数量
        int levelSize = 1;

        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
            if (--levelSize == 0) { // 即将访问下一层
                levelSize = q.size();
                ++height;
            }
        }
        return height;
    }

    /*
        一棵树的高度等于所有节点高度的最大值
     */
    public int height2() {
        return height(root);
    }

    public int height(Node<E> node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /*
        BinaryTreeInfo
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> n = (Node<E>) node;
        String parentString;
        if (n.parent == null) {
            parentString = "null";
        } else {
            parentString = n.parent.elem.toString();
        }
        return n.elem + "(" + parentString + ")";
    }
}
