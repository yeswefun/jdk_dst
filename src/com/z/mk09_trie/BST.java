package com.z.mk09_trie;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
二叉搜索树(Binary Search Tree, BST):
    二叉搜索树的元素必须具有可比较性
 */
class BST<E extends Comparable<E>> {

    //------------------------------------------------------ 基础相关，开始
    private static class Node<E> {
        public E elem;
        public Node<E> left, right;

        public Node(E elem) {
            this.elem = elem;
            left = right = null;
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

    private Node<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    //------------------------------------------------------ 基础相关，结束


    //------------------------------------------------------ 删除相关，开始

    public void remove(E elem) {
        root = remove(root, elem);
    }

    //删除掉以node为根的二分搜索树中值为elem的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node<E> remove(Node<E> node, E elem) {

        if (node == null) {
            return null;
        }

        int ret = elem.compareTo(node.elem);
        if (ret < 0) {
            node.left = remove(node.left, elem);
            return node;
        } else if (ret > 0) {
            node.right = remove(node.right, elem);
            return node;
        } else {
            //删除最小
            if (node.left == null) {
                Node<E> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //删除最大
            if (node.right == null) {
                Node<E> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //node.left != null && node.right != null
            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node<E> successor = minimum(node.right);
            successor.right = removeMin(node.right); // size--
            //size++, 实际 removeMin 删除掉的节点就是 successor,
            //而removeMin里面做了size--, 所以此处 size++, 即 removeMin 前后 size 不变
            successor.left = node.left;
            node.left = node.right = null;
            //size--, 上面加，这里减，所以可以消除掉
            return successor;
        }
    }

    /*
        特殊情况:
                5
        情况一:
                5
              /    \
             3      6
            / \    /  \
           2   4  7   [8]
        情况二:
                5
              /    \
             3     [6]
            / \    /
           2   4  7
     */
    //从二分搜索树中删除最大值所在节点
    public E removeMax() {
        E ret = maximum();
        //root != null
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除节点后，新的二分搜索树的根
    private Node<E> removeMax(Node<E> node) {//root != null, but only has root node
        //不可能是同时拥有左右子节点，因为最大值必然是最右边的节点
        //1.叶子节点(childCount == 0, leftNode == null)
        //2.只带左子树的节点(childCount == 1, leftNode != null)
        if (node.right == null) {
            Node<E> leftNode = node.left;
            node.left = null;
            --size;
            return leftNode;
        }
        node.right = removeMax(node.right);
        //node.right != null
        return node;
    }

    /*
        特殊情况:
                5
        情况一:
                5
              /    \
             3      6
            / \       \
           [2] 4       8
        情况二:
                5
              /    \
            [3]      6
              \       \
               4       8
     */
    //注: 如果不是用户指定删除哪个元素，一般都需要返回被删除元素
    //从二分搜索树中删除最大值所在节点
    public E removeMin() {
        E ret = minimum();
        //root != null
        root = removeMin(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后，新的二分搜索树的根
    private Node<E> removeMin(Node<E> node) {//root != null, but only has root node
        //不可能是同时拥有左右子节点，因为最小值必然是最左边的节点
        //1.叶子节点(childCount == 0, rightNode == null)
        //2.只带右子树的节点(childCount == 1, rightNode != null)
        if (node.left == null) {
            Node<E> rightNode = node.right;
            node.right = null;
            --size;
            return rightNode;
        }
        node.left = removeMin(node.left);
        //node.left != null
        return node;
    }

    /*
        寻找二分搜索树的最大元素

        情况一:
                5
              /    \
             3      6
            / \    /  \
           2   4  7   [8]
        情况二:
                5
              /    \
             3     [6]
            / \    /
           2   4  7
     */
    public E maximum() {
        //root == null
        if (size == 0)
            throw new IllegalStateException("BST is empty");
        //root != null
        return maximum(root).elem;
    }

    private Node<E> maximum(Node<E> node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    /*
    public E maximum() {
        if (size == 0)//root == null
            throw new IllegalStateException("BST is empty");
        Node<E> node = root;
        while (node.right != null)
            node = node.right;
        return node.elem;
    }
     */

    /*
        寻找二分搜索树的最小元素

        情况一:
                5
              /    \
             3      6
            / \       \
           [2] 4       8
        情况二:
                5
              /    \
            [3]      6
              \       \
               4       8
     */
    public E minimum() {
        //root == null
        if (size == 0)
            throw new IllegalStateException("BST is empty");
        //root != null
        return minimum(root).elem;
    }

    private Node<E> minimum(Node<E> node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }
    /*
    public E minimum() {
        if (size == 0)//root == null
            throw new IllegalStateException("BST is empty");
        Node<E> node = root;
        while (node.left != null)
            node = node.left;
        return node.elem;
    }
     */
    //------------------------------------------------------ 删除相关，结束

    //------------------------------------------------------ 遍历相关，开始
    //广度优先，BFS，队列
    //搜索
    public void levelOrder() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node);
            //FIFO
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    //深度优先，DFS，栈
    //前，中，后 序遍历都是深度优先的遍历
    public void preOrderNR() {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            System.out.println(node);
            //LIFO
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    //后序遍历(先遍历左右子树，再访问根节点)
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
    }

    //中序遍历(先遍历左子树，再访问根节点，然后遍历右子树)
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    //先序遍历(先访问根节点，再访问左右子树)
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树
    private void preOrder(Node<E> node) {
        if (node == null)
            return;
        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }
    //------------------------------------------------------ 遍历相关，结束

    /*
                5
              /    \
             3      6
            / \    /  \
           2   4  7    8
     */
    public boolean contains(E elem) {
        return contains(root, elem);
    }

    private boolean contains(Node<E> node, E elem) {

        if (node == null)
            return false;

        int ret = elem.compareTo(node.elem);
        if (ret == 0) {
            return true;
        } else if (ret < 0) {
            return contains(node.left, elem);
        } else {
            return contains(node.right, elem);
        }
    }

    /*
        向二分搜索树中添加新的元素elem
                5
              /    \
             3      6
            / \    /  \
           2   4  7    8
     */
    public void add(E elem) {
        root = add(root, elem);
    }

    //向以node为根的二分搜索树中插入元素elem，递归算法
    //返回插入新节点后，二分搜索树的根
    private Node<E> add(Node<E> node, E elem) {

        // node == root && node == null
        // node != root && node == null
        if (node == null) {
            ++size;
            return new Node<>(elem);
        }

        int ret = elem.compareTo(node.elem);
        if (ret < 0) {
            node.left = add(node.left, elem);
        } else if (ret > 0) {
            node.right = add(node.right, elem);
        }
        //else, equal -> do nothing

        return node;
    }

    /*
    public void add(E elem) {
        if (root == null) {
            root = new Node<>(elem);
            ++size;
        } else {
            add(root, elem);
        }
    }

    private void add(Node<E> node, E elem) {

        int ret = elem.compareTo(node.elem);
        if (ret == 0) { // elem.equals(node.elem)
            //node.elem = elem;
            return;
        } else if (ret < 0 && node.left == null) {
            node.left = new Node<>(elem);
            ++size;
            return;
        } else if (ret > 0 && node.right == null) {
            node.right = new Node<>(elem);
            ++size;
            return;
        }
        //else不能作为node的子节点

        //只有小于或大于
        if (ret < 0) {
            add(node.left, elem);
        } else {
            add(node.right, elem);
        }
    }
    */
    public void add2(E elem) {

        if (root == null) {
            root = new Node<>(elem);
            ++size;
            return;
        }

        //root != null
        int ret;
        Node<E> parent;
        Node<E> node = root;
        do {
            parent = node;
            ret = elem.compareTo(node.elem);
            if (ret < 0) {
                node = node.left;
            } else if (ret > 0) {
                node = node.right;
            } else {
                // equal
                return;
            }
        } while (node != null);

        Node<E> newNode = new Node<>(elem);
        // 结果只有 小于 或 大于
        if (ret < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        ++size;
    }

    public String postOrderString() {
        StringBuilder sb = new StringBuilder();
        postOrderString(root, 0, sb);
        return sb.toString();
    }

    private void postOrderString(Node<E> node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth))
                    .append("null")
                    .append(System.lineSeparator());
            return;
        }

        postOrderString(node.left, depth + 1, sb.append("[L] "));
        postOrderString(node.right, depth + 1, sb.append("[R] "));

        sb.append(generateDepthString(depth))
                .append(node.elem)
                .append(System.lineSeparator());
    }

    public String inOrderString() {
        StringBuilder sb = new StringBuilder();
        inOrderString(root, 0, sb);
        return sb.toString();
    }

    private void inOrderString(Node<E> node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth))
                    .append("null")
                    .append(System.lineSeparator());
            return;
        }

        inOrderString(node.left, depth + 1, sb.append("[L] "));

        sb.append(generateDepthString(depth))
                .append(node.elem)
                .append(System.lineSeparator());

        inOrderString(node.right, depth + 1, sb.append("[R] "));
    }

    public String preOrderString() {
        return toString();
    }

    /*
        默认: 先序遍历
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preStr(root, 0, sb.append("Root: "));
        return sb.toString();
    }

    //先序遍历 - 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void preStr(Node<E> node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth))
                    .append("null")
                    .append(System.lineSeparator());
            return;
        }

        sb.append(generateDepthString(depth))
                .append(node.elem)
                .append(System.lineSeparator());

        preStr(node.left, depth + 1, sb.append("[L] "));
        preStr(node.right, depth + 1, sb.append("[R] "));
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("----\t");
        }
        return sb.toString();
    }
}
