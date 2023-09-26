package com.z.mk03_queue;

class LinkedQueue<E> implements IQueue<E> {

    private Node<E> head, tail;
    private int size;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    /*
        对外部不可见，只知道元素类型
     */
    private static class Node<E> {
        public E elem;
        public Node<E> next;

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }

        public Node(E elem) {
            this(elem, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        return head.elem;
    }

    /*
        入队 与 出队 都需要处理 队列中只有一个元素 的情况
     */
    @Override
    public void enqueue(E elem) {
        if (head == null) {// head == null && tail == null, 此时队列为空(size == 0)
            head = tail = new Node<>(elem);
        } else {
            //tail.next = new Node<>(elem);
            //tail = tail.next;
            tail = tail.next = new Node<>(elem);
        }
        ++size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        Node<E> node = head;
        head = node.next; // head = head.next;
        node.next = null;
        if (head == null) { //只有一个元素进行出队操作, 此时 head == null, but tail != null
            tail = null;
        }
        --size;
        return node.elem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedQueue: front ");
        Node<E> cur = head;
        while (cur != null) {
            sb.append(cur.elem).append(" -> ");
            cur = cur.next;
        }
        sb.append("NUll tail");
        return sb.toString();
    }
}
