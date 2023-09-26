package com.z.mk_sum;

/*
数组的基本使用
    int[] a = new int[] {1, 2, 3};
    int[] b = {1, 2, 3};  // 只能作为初始化时使用
          b = {1, 2, 3};  // error
          test({1, 2, 3});// error

    int[] d = new int[3];
    d[0] = 1;
    d[1] = 2;
    d[2] = 3;

    字符串与数组长度
        String str = "test"; // str.length();

        String[] arr = new String[3]; // arr.length



动态数组
    add, 扩容
    remove，缩容

    均摊复杂度
    复杂度震荡
        错开扩容与缩容的边界

    删除
        E remove(int index);
        void remove(E elem);

    泛型数组
        E[] arr = (E[]) new Object[capacity];



链表
    需要找到前一个位置

    add
        头部
        中间
        尾部 - 添加慢
            双向链表

    remove

    虚拟头节点
        统一 头部 和 中间 的操作
            add
            remove

        只有 add 和 remove 需要得到 index 的前一个节点,
            从有效的前一个位置开始，即从 dummyHead 开始

        set, get, contains 都是从 dummyHead.next 开始
            从有效的位置开始，即从 dummyHead.next 开始


********************************* 栈
    FILO
    只允许操作 栈顶 元素

顺序栈
    数组尾部作为栈顶
        添加元素，O(1)
        删除元素，O(1)

链式栈
    链表头部作为栈顶
        添加元素，O(1)
        删除元素，O(1)


顺序栈 与 链式栈
    顺序栈会出现 动态扩容和缩容 的情况




********************************* 队列
    FIFO
    固定的一端入队，固定的另一端出队

    入队，在队尾进行添加元素
    出队，在队头进行删除元素

    队头，第一个元素
    队尾，最后一个元素

顺序队列
    情况一(练习中)
        队头: 数组头部，O(n)，前移元素
        队尾: 数组尾部，O(1)
    情况二
        队头: 数组尾部，O(1)
        队尾: 数组头部，O(n), 后移元素

    思考: 顺序循环队列

链式队列
    情况一
        队头: 链表头部，O(1)
        队尾: 链表尾部，O(n)，遍历到尾部
    情况二
        队头: 链表尾部，O(n)，遍历到尾部
        队尾: 链表头部，O(1)

    思考: 双端链表


顺序队列 与 链式队列




循环队列
    一个指向队列头front, 一个指向队列尾rear。

    在循环队列中，
        当队列为空时，有 front = rear;
        当队列为满时，有 front = rear。

    所以，为了区别这两种情况，规定循环队列最多只能有 MaxSize-1 个队列元素(也就是空一个位置)，
    当循环队列中只剩下一个空存储单元时，队列就已经满了。

    因此，
        队列判空的条件是 front = rear，
        队列判满的条件是 front = (rear+1) % MaxSize。

    综上：这个空位置，主要就是为了用来区分队空与队满情况的。



顺序循环队列
    解决队头删除元素 O(n) 的问题
        避免元素移动

    head, rear
        head == rear, 空队列
        (rear + 1) % length == head, 满队列

    add
        rear = (rear + 1) % length;

    remove
        head = (head + 1) % length;

    length
        计算元素个数: 可以分两种情况判断：
            如果 rear >= front: 元素个数为 rear-front
                rear == front: 队列为空, rear - front == 0
                rear >  front: 队列不为空, rear - front == size

            如果 rear <  front: 元素个数为 rear-front+Maxsize

            采用取模的方法把两种情况统一为：(rear-front+Maxsize)%Maxsize

    假溢出
    真溢出

    取模运算的优化: mj


ArrayQueue 与 LoopQueue


Linear的时间复杂度分析
 */
public interface Z00_Linear {
}
