package com.z.d06_bst;

import com.z.d06_bst.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
BST: Binary Search Tree

implements BinaryTreeInfo
    为了打印二叉树
 */
class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;

    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
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
        do {
            //...
        } while(node != null);
     */
    public void add(E elem) {
        elemNotNullCheck(elem);

        if (root == null) {
            root = new Node<>(elem, null);
            ++size;
            return;
        }

        int ret = 0;
        Node<E> parent = root;
        Node<E> node = root;
        while (node != null) { // 上来的第一次就肯定成立
            parent = node;
            ret = compare(elem, node.elem);
            if (ret > 0) { // 右边，大
                node = node.right;
            } else if (ret < 0) { // 左边，小
                node = node.left;
            } else { // ret == 0，相等
                //return; // 相等直接返回
                node.elem = elem; //其它字段发生了修改
                return;
            }
        }

        Node<E> newNode = new Node<>(elem, parent);
        if (ret > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        ++size;
    }

    /*
        先根据 元素 查找 节点，再将 节点 给删除
     */
    public void remove(E elem) {
        remove(node(elem));
    }

    /*
        节点的度为0，叶子节点
            直接删除
            左叶子节点，node.parent.left = null
            右叶子节点，node.parent.right = null
        节点的度为1，左子节点或右子节点
            左子节点
                child.parent = node.parent;
                node.parent.left = child;
            右子节点
                child.parent = node.parent;
                node.parent.right = child
        节点的度为2，
            先用前驱或后继节点的值覆盖原节点的值，
            然后删除相应的前驱或后继节点

        TODO: 这句话有点歧义
            如果一个节点的度为2，那么
            它的前驱，后继节点的度只可能是 1 和 0
     */
    private void remove(Node<E> node) {

        if (node == null)
            return;

        --size;

        if (node.hasTwoChildren()) { // 度为2
            Node<E> s = successor(node);
            //用前驱或后继节点的值覆盖原节点的值
            node.elem = s.elem;
            //然后删除相应的前驱或后继节点
            node = s;
        }

        //度为0或1，删除node节点
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // 度为1
            replacement.parent = node.parent;
            if (node.parent == null) { // node 度为0，叶子节点且不为root节点
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            }
        } else if (node.parent == null) { // 度为0，叶子节点且为root节点
            root = null;
        } else { // 度为0，叶子节点且不为root节点
            // 判断 node 是 node.parent 的左边还是右边
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    /*
        先左，后右
     */
    private Node<E> node(E elem) {
        Node<E> node = root;
        while (node != null) {
            int ret = compare(elem, node.elem);
            if (ret < 0) {
                node = node.left;
            } else if (ret > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /*
        Node<E>对外界不可见
     */
    public boolean contains(E elem) {
        return node(elem) != null;
    }

    /*
        后继节点
            中序遍历时目标节点的后一个节点

        找到 node 的后继节点
     */
    public Node<E> successor(Node<E> node) {

        /*
            node.right 和 node.parent 要求 node 不能为空
         */
        if (node == null)
            return node;

        /*
            后继节点在右子树(node.right.left.left.left...)
                4(-)
                    8
                  7   10
                5(+)
         */
        Node<E> next = node.right;
        if (next != null) {
            while (next.left != null) {
                next = next.left;
            }
            return next;
        }

        /*
            从父节点，祖父节点中查找后继节点
              4
                 8(+)
              7(+)  10(-)
            5(-)
         */
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        // node.parent == null
        // node.parent.left == node
        return node.parent;
    }

    /*
        前驱节点
            中序遍历时目标节点的前一个节点

        找到 node 的前驱节点
     */
    public Node<E> predecessor(Node<E> node) {

        /*
            node.left 和 node.parent 要求 node 不能为空
         */
        if (node == null)
            return node;

        /*
            前驱节点在左子树(node.left.right.right.right...)
                    8(-)
                 4
               2    6
                       7(+)
         */
        Node<E> prev = node.left;
        if (prev != null) {
            while (prev.right != null) {
                prev = prev.right;
            }
            return prev;
        }

        /*
            从父节点，祖父节点中查找前驱节点
                    8(+)
                        13
                     10
                   9(-) 12
         */
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        // node.parent == null
        // node.parent.right == node
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
        前序
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void levelorderTraversal(Visitor<E> visitor) {
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
        0 : e1 == e2
        >0: e1 > e2
        <0: e1 < e2
     */
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        //Comparable
        //class BinarySearchTree<E extends Comparable<E>> {}
        //return e1.compareTo(e2);

        //Comparator
        //return comparator.compare(e1, e2);

        //先 Comparable，后 Comparator
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elemNotNullCheck(E elem) {
        if (elem == null) {
            throw new IllegalStateException("elem cannot be null");
        }
    }

    /*
        节点 Node 对外不可见，可见的是 elem
     */
    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E elem);
        //void visit(E elem);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null)
            return;
        toString(node.left, sb, prefix + "L***");
        sb.append(prefix).append(node.elem).append("\n");
        toString(node.right, sb, prefix + "R---");
    }

    /*
        层序遍历
        为了不让外面报错 - 该方法逻辑固定，仅用于调试
     */
    @Deprecated
    public void levelorderTraversal() {
        if (root == null)
            return;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            System.out.print(node.elem + " "); // 访问逻辑
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    /*
        后序遍历
        为了不让外面报错 - 该方法逻辑固定，仅用于调试
     */
    @Deprecated
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    @Deprecated
    private void postorderTraversal(Node<E> node) {
        if (node == null)
            return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.elem + " ");
    }

    /*
        中序遍历，可以尝试将 node.left 与 node.right 上下交换一下
        为了不让外面报错 - 该方法逻辑固定，仅用于调试
     */
    @Deprecated
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    @Deprecated
    private void inorderTraversal(Node<E> node) {
        if (node == null)
            return;
        inorderTraversal(node.left);
//        inorderTraversal(node.right);
        System.out.print(node.elem + " ");
//        inorderTraversal(node.left);
        inorderTraversal(node.right);
    }

    /*
        前序遍历
        为了不让外面报错 - 该方法逻辑固定，仅用于调试
     */
    @Deprecated
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    @Deprecated
    private void preorderTraversal(Node<E> node) {
        if (node == null)
            return;
        System.out.print(node.elem + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
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
