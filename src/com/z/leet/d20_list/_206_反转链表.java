package com.z.leet.d20_list;

/*
https://leetcode.cn/problems/reverse-linked-list/

给你单链表的头节点 head ，请你反转链表，并返回反转后的链表

输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

输入：head = [1,2]
输出：[2,1]

输入：head = []
输出：[]

提示：
    链表中节点的数目范围是 [0, 5000]
    -5000 <= Node.val <= 5000

进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
class _206_反转链表 {

    public class ListNode {
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
    }

    /*
        没有虚拟头节点的情况下

        一个节点指向后一个: next
        一个节点指向前一个: prev
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /*
        anwser - 递归
            要想递归函数的作用是什么

        if (head == null) { // 没有节点
            return null;
        }
        if (head.next == null) { // 只有一个节点
            return head;
        }

        * -> n1 -> n2 -> n3 -> null

        * -> n1
            newHead -> n3 -> n2 -> null

            n1->next == n2
            n2->next == null

            n1->next->n2->next -> n1
            n1->next -> null
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 深度遍历，操作都递归点后面
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /*
        anwser - 循环

        head -> 1 -> 2 -> 3 -> 4 -> null
        null <- 1 <- 2 <- 3 <- 4 <- head
     */
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode cur = head;
//        ListNode prev = null;
//        ListNode next;
//        while (cur != null) {
//            next = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = next;
//        }
//        return prev;
//    }
}
