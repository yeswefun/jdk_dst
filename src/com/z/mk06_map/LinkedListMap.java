package com.z.mk06_map;


class LinkedListMap<K, V> implements IMap<K, V> {

    /*
        对外部不可见，只知道元素类型
     */
    private static class Node<K, V> {

        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node<K, V> dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node<>();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<K, V> getNode(K key) {
        Node<K, V> cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    /*
        key 存在，才修改
        key 不存在，报错
     */
    @Override
    public void set(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            throw new RuntimeException("key doesn't exist: " + key);
        }
        node.value = value;
    }

    @Override
    public void add(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node<>(key, value, dummyHead.next);
            size++;
        } else {
            //有，则修改，此处也可以抛出异常
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node<K, V> prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {
            Node<K, V> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            --size;
            return delNode.value;
        }
        return null;
    }
}
