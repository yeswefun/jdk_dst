package com.z.leet.d20_list;

/*
https://leetcode.cn/problems/remove-linked-list-elements/

给你一个链表的头节点 head 和一个整数 val ，
请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点

示例 1：
    输入：head = [1,2,6,3,4,5,6], val = 6
    输出：[1,2,3,4,5]

示例 2：
    输入：head = [], val = 1
    输出：[]

示例 3：
    输入：head = [7,7,7,7], val = 7
    输出：[]

提示：
    列表中的节点数目在范围 [0, 104] 内
    1 <= Node.val <= 50
    0 <= val <= 50
 */
class _203_移除链表元素 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        /*
            不带虚拟头节点
            当前的ListNode为链表头结点
         */
        ListNode(int[] arr) {
            if (arr == null || arr.length == 0)
                throw new IllegalArgumentException("arr == null || arr.length == 0");
            //do ... while();
            val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                sb.append(cur.val).append(" -> ");
                cur = cur.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    /*
        不带虚拟头节点
     */
    public ListNode removeElements(ListNode head, int val) {

        // [3 -> 3] -> 4 -> 6 -> 3 -> 3 -> 2 -> null
        // [3 -> 3] -> null
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return head; // head == null, return null;
        }

        // 在此处已经确定返回值为 head
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    /*
        不带虚拟头节点 - 不断开被删除节点的next指向
     */
    public ListNode removeElements2(ListNode head, int val) {

        // [3 -> 3] -> 4 -> 6 -> 3 -> 3 -> 2 -> null
        // [3 -> 3] -> null
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;
        }

        // 在此处已经确定返回值为 head
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    /*
        带虚拟头节点 - 不断开被删除节点的next指向
            通过 虚拟头节点 统一 头部 和 中间 的操作
     */
    public ListNode removeElements3(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    /*
        递归
        宏观: 删除掉链表中值为val的节点

         1 -> 2(x) -> 3 -> null
         |
        head

        fn(1, 2)
            ret = fn(2, 2)
                ret = fn(3, 2)
                    ret = fn(null, 2)
                        = null
                    head.val == 2
        <---------- 从后往前，有条件的拼接
        ret 表示 从后往前拼接好的链表的头节点
     */
    public ListNode removeElementsR(ListNode head, int val) {

        //递归出口，链表头，链表尾
        if (head == null) {
            return null;
        }

        //微观: 删除掉head之后的链表中值为val的节点
        //规模缩小
        //结果的含义: 以 head 的下一个节点 为 头节点的链表，该链表已经完成删除元素的操作
        ListNode ret = removeElementsR(head.next, val);

        //通过得到的 解 构建 原问题的解
        //当前层的处理
        if (head.val == val) {
            return ret;
        } else {
            head.next = ret;
            return head;
        }
    }

    /*
        递归 - 简化
     */
    public ListNode removeElementsR2(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        head.next = removeElementsR2(head.next, val);

        //return head.val == val ? head.next : head;
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        int[] numArr = {3, 3, 1, 2, 3, 3, 2, 1, 3};
        ListNode head = new ListNode(numArr);

        System.out.println("*************************** head");
        System.out.println(head);

        System.out.println("*************************** head2");
        _203_移除链表元素 instance = _203_移除链表元素.class.newInstance();
//        ListNode head2 = instance.removeElements(head, 3);
//        ListNode head2 = instance.removeElements2(head, 3);
//        ListNode head2 = instance.removeElements3(head, 3);
//        ListNode head2 = instance.removeElementsR(head, 3);
        ListNode head2 = instance.removeElementsR2(head, 3);
        System.out.println(head2);

        System.out.println("*************************** head");
        System.out.println(head);
    }
}
