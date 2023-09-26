package com.z.mk11_avl;


/*
底层使用 二分搜索树，
二分搜索树 的 元素 需要 具备 可比性

去掉了 IMap<K, V>
 */
class BST<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node<K, V> root;
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

    //向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树中插入元素(key, value)，递归算法
    //返回插入新节点后二分搜索树的根
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
}

