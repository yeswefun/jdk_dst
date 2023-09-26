package com.z.d02_list;

/*
List
    时间复杂度
        get
        set
        add
            size == 0
        remove
            size != 0, 因为有 rangeCheck(index);


    ArrayList - 底层实现为 数组
        add: 往后移动
        remove: 往前移动

        扩容操作: 在添加元素时
        缩容操作: 在删除元素时


    LinkedList - 底层实现为 链表
        带虚拟头节点的单向链表
            ...
        不带虚拟头节点的单向链表
            add, remove - 获取 index 的前一个元素
                index == 0


    双向链表
        add
            index == size
                在最后一个后面添加新元素，index == size, size != 0
                添加第一个元素, index == size, size == 0
        remove
            所有情况
                前驱 != null, 后继 != null
                前驱 != null, 后继 == null
                前驱 == null, 后继 != null
                前驱 == null, 后继 == null

        first == last == null, 没有元素, size == 0
        first.prev == null
        last.next == null


    循环链表
        单向循环链表 - add和remove 与 单向链表类似
            尾节点的后继节点为 头节点
                在 index == 0 添加节点，需要将 尾节点 的 next 指向 新的头节点

            add
                index == 0
                    size == 0
            remove
                index == 0
                    size == 1

        双向循环链表 - add和remove 与 双向链表类似
            头节点的前驱节点为 尾节点
            尾节点的后继节点为 头节点

            add
                index == size
                    在最后一个后面添加新元素
                    添加第一个元素, index == 0, size == 0
                index != size, 至少两个元素
            remove
                size == 1
                    first = last = null 
                size ！= 1，至少两个元素(因为有rangeCheck)
                    断开 node, 类似 双向链表
                    index == 0, first
                    index == size-1, last

    静态链表
        通过数组模拟链表
        数组每个元素存放2个数据
            值
            下一个元素的索引
        数组0位置存放的是头节点信息
        数组元素值为 -1 表示结束，因为索引值不存在 -1


头插法
    node.next = first;
    first = node;
尾插法
    node.prev = last;
    last = node;


动态数组与链表
    动态数组需要动态扩容或缩容
    链表是不需要动态扩容或缩容


单向链表与双向链表
    单向: node(index-1)
    双向: node(index)
    注: 双向链表查找节点比单向链表查找快


双向链表的添加 - add
    index == size
        size == 0
        size != 0
    index != size
        index == 0, size != 0


位移运算优先级
    int oldCapacity = 4;
    int newCapacity = oldCapacity + oldCapacity >> 1;
    oldCapacity == 4
    newCapacity == 4


    int oldCapacity = 4;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    oldCapacity == 4
    newCapacity == 8
 */
interface Z_List {
}
