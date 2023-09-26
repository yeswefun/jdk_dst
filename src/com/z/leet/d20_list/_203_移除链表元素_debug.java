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
class _203_移除链表元素_debug {

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

    //宏观: 删除掉链表中值为val的节点
    public ListNode removeElementsR(ListNode head, int val, int depth) {

        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        //递归出口
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }

        //微观: 删除掉head之后的链表中值为val的节点
        ListNode res = removeElementsR(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After: remove " + val + " : " + res);

        //通过得到的解构建原问题的解
        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }

        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("----\t");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        int[] numArr = {0, 1, 2, 3};
        ListNode head = new ListNode(numArr);
        System.out.println(head);

        _203_移除链表元素_debug instance = _203_移除链表元素_debug.class.newInstance();
        ListNode head2 = instance.removeElementsR(head, 3, 0);
        System.out.println(head2);
        System.out.println(head);
    }
}
