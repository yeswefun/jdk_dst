package com.z.mk14_hash;

import java.util.ArrayList;

/*
在 BSTMap 的基础上加入自平衡机制

AVLTree 是对 BST 的改进
    BST 有可能退化成链表
        而 AVLTree 引入 平衡因子 就是为了解决一个问题
            每个节点左右子树高度差不超过 1
            AVLTree 也是 二分搜索树，所以 AVLTree 也要满足 二分搜索树 的性质
            二分搜索树 的性质
                每个节点 的 左子树中的所有节点的值 都要 小于 该节点的值
                每个节点 的 右子树中的所有节点的值 都要 大于 该节点的值
                左右子树 也是 二分搜索树

AVLTree 经过 添加，删除 后，还是一棵 平衡二叉树 & 二叉搜索树

添加一个节点
    LL
        左旋转
    RR
        右旋转
    LR
    RL

把未知的问题转化为已知的问题;把待解决的问题归结为已解决的问题, 从而解决问题
    化归


AVLTree的优化
    如果重新计算出来的高度与该节点原来高度相等，
    那么该节点的父节点，及祖先节点都不需要重新计算高度

AVLTree的局限性
    红黑树的平均性能比AVLTree更优
    类似，快速排序的平均性能比归并排序更优
 */
class AVLTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;

        //当前节点的高度值
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;

            //在 BST 中 添加节点，该节点必定是叶子节点
            //每个新建的节点都是叶子节点
            height = 1;
        }
    }

    private Node<K, V> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int getHeight(Node<K, V> node) {//警告如何消除?
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获取节点node的平衡因子
    private int getBalanceFactor(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断 二叉树 是否是 一棵 二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node<K, V> node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断 二叉树 是否是 一棵 平衡二叉树
    //每个节点左右子树高度差不超过 1
    public boolean isBalanced() {
        //从根节点开始判断左右子树高度差不超过1
        return isBalanced(root);
    }

    //判断以 node 为根的二叉树是否是一棵平衡二叉树
    private boolean isBalanced(Node<K, V> node) {
        if (node == null) { //空树
            return true;
        }
        int factor = getBalanceFactor(node);
        if (Math.abs(factor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点 y 进行向右旋转操作，返回旋转后新的根节点x
    //          y                            x
    //         / \                         /    \
    //        x   T4    向右旋转(y)        z       y
    //       / \        --------->      /  \     / \
    //      z  T3                      T1   T2  T3  T4
    //     / \
    //    T1  T2
    //
    //y相对于x(后来的新根)向右旋转了
    private Node<K, V> rightRotate(Node<K, V> y) {

        Node<K, V> x = y.left;
        Node<K, V> T3 = x.right;
        //y.left = null; //后面会重新赋值

        //向右旋转
        x.right = y;
        y.left = T3;

        //更新height, x的高度受y的高度影响
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        //x.height = Math.max(getHeight(x.left), y.height) + 1;

        return x;
    }

    // 对节点 y 进行向左旋转操作，返回旋转后新的根节点x
    //          y                            x
    //         / \                         /    \
    //       T1   x     向左旋转(y)        y       z
    //           / \    --------->      /  \     / \
    //          T2  z                  T1   T2  T3  T4
    //             / \
    //            T3  T4
    //
    //y相对于x(后来的新根)向左旋转了
    private Node<K, V> leftRotate(Node<K, V> y) {

        Node<K, V> x = y.right;
        Node<K, V> T2 = x.left;

        //向左旋转
        x.left = y;
        y.right = T2;

        //更新height, x的高度受y的高度影响
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        //x.height = Math.max(getHeight(x.left), y.height) + 1;

        return x;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树中插入元素(key, value)，递归算法
    //返回插入新节点后二分搜索树的根
    private Node<K, V> add(Node<K, V> node, K key, V value) {

        if (node == null) {
            size++;
            //创建叶子节点，高度默认为 1
            return new Node<>(key, value);
        }

        int ret = key.compareTo(node.key);
        if (ret < 0) {
            node.left = add(node.left, key, value);
        } else if (ret > 0) {
            node.right = add(node.right, key, value);
        } else {
            //有，则修改，此处也可以抛出异常
            node.value = value;
        }

        //--- 添加节点完毕  ---

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int factor = getBalanceFactor(node);
//Before
//        if (Math.abs(factor) > 1) {//维护自平衡
//            System.out.println("--- unbalanced: " + factor);
//        }

        //--- 维护平衡性 --- 二叉搜索树, 平衡二叉树 的性质得到维持
        //factor = getHeight(node.left) - getHeight(node.right)

        //整个向左倾斜
        //LL
        if (factor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        //整个向右倾斜
        //RR
        if (factor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        //LR
        if (factor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left); //转化成 LL 的情况
            return rightRotate(node);
        }

        //RL
        if (factor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right); //转化成 RR 的情况
            return leftRotate(node);
        }
//After
//        if (Math.abs(factor) > 1) {//维护自平衡
//            System.out.println("--- unbalanced: " + factor);
//        }

        //返回维护完平衡性的节点
        return node;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int ret = key.compareTo(node.key);
        if (ret == 0) {
            return node;
        } else if (ret < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /*
        key 存在，才修改
        key 不存在，报错
     */
    public void set(K key, V value) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            throw new RuntimeException("key doesn't exist: " + key);
        }
        node.value = value;
    }

    //返回以node为根的二分搜索树的最小值所在的节点
    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
//    private Node<K, V> removeMin(Node<K, V> node) {
//        if (node.left == null) {
//            Node<K, V> rightNode = node.right;
//            node.right = null;
//            size--;
//            return rightNode;
//        }
//        node.left = removeMin(node.left);
//        return node;
//    }

    //删除掉以node为根的二分搜索树中键为key的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        Node<K, V> retNode;
        int ret = key.compareTo(node.key);
        if (ret < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (ret > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            //ret == 0

            //待删除节点只存在右孩子
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                --size;
                retNode = rightNode;
            } else if (node.right == null) { //待删除节点只存在左孩子
                Node<K, V> leftNode = node.left;
                node.left = null;
                --size;
                retNode = leftNode;
            } else {
                //node.left != null && node.right != null
                //待删除节点存在左右孩子
                Node<K, V> successor = minimum(node.right);
                //successor.right = removeMin(node.right); //可能打破平衡
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        //删除完一个节点，返回的二叉树的根节点为null
        if (retNode == null) {
            return null;
        }

        //--- 维护平衡性 ---

        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int factor = getBalanceFactor(retNode);

        //整个向左倾斜
        //LL
        if (factor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        //整个向右倾斜
        //RR
        if (factor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        //LR
        if (factor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left); //转化成 LL 的情况
            return rightRotate(retNode);
        }

        //RL
        if (factor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right); //转化成 RR 的情况
            return leftRotate(retNode);
        }

        return retNode;
    }

    //从二分搜索树中删除键为key的节点
    public V remove(K key) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }
}
