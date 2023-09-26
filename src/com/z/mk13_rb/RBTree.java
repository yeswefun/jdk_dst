package com.z.mk13_rb;

/*
在 BSTMap 的基础上进行修改
 */
class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;
        //true: red, false: black
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            //默认是红色
            //2-3树中添加的节点永远都是与叶子节点进行融合
            //红黑树中 红色节点 代表 该节点 和 其父节点 在 2-3树 中是合并在一起的三节点
            color = RED;
        }
    }

    private Node<K, V> root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isRed(Node<K, V> node) {
        if (node == null) {
            //3.每一个叶子节点(最后的空节点, 而不是二叉树中左右子树为空的节点)是黑色的
            return BLACK;
        }
        return node.color;
    }

    //   node                        x
    //   /  \         左旋转        /   \
    //  T1   x      --------->   node   T3
    //      /  \                 /  \
    //     T2  T3              T1   T2
    private Node<K, V> leftRotate(Node<K, V> node) {
        Node<K, V> x = node.right;
        //左旋转
        node.right = x.left;
        x.left = node;
        //颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //   node                        x
    //   /  \         右旋转        /   \
    //  x   T2      --------->    y    node
    // /  \                            /  \
    //y   T1                          T1   T2
    private Node<K, V> rightRotate(Node<K, V> node) {
        Node<K, V> x = node.left;
        //右旋转
        node.left = x.right;
        x.right = node;
        //颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //向红黑树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
        //最终根节点为黑色
        root.color = BLACK;
    }

    //向以node为根的红黑树中插入元素(key, value)，递归算法
    //返回插入新节点后红黑树的根
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
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

        //--- 红黑树性质维护 ---
        //左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        //右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        //颜色翻转
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

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
    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //删除掉以node为根的二分搜索树中键为key的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int ret = key.compareTo(node.key);
        if (ret < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (ret > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            //ret == 0

            //待删除节点只存在右孩子
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                --size;
                return rightNode;
            }

            //待删除节点只存在左孩子
            if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //node.left != null && node.right != null
            //待删除节点存在左右孩子
            Node<K, V> successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
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

    public static void main(String[] args) {

    }
}