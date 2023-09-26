package com.z.leet.d20_list;

/*
https://leetcode.cn/problems/linked-list-cycle/

给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）
注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true, 否则，返回 false

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点

输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点

输入：head = [1], pos = -1
输出：false
解释：链表中没有环

提示：
    链表中节点的数目范围是 [0, 104]
    -105 <= Node.val <= 105
    pos 为 -1 或者链表中的一个 有效索引

进阶：你能用 O(1)（即常量）内存解决此问题吗？
 */
class _141_环形链表 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
        快慢指针
            slow(每次走一步), fast(每次走两步)

        使用循环遍历链表时，
            若不存在环，则是有限循环
            若存在环，则是死循环
                每次fast比slow多走一步

                slow == n, fast == n+1
                设置第 x 次循环，slow 与 fast 相遇

        没有环的情况
            * -> null

            * -> 1 -> null

            * -> 1 -> 2 -> null
                 s----f

            * -> 1 -> 2 -> 3 -> null
                 s----f
                      s-----------f

            * -> 1 -> 2 -> 3 -> 4 -> null
                 s----f
                      s---------f

        有环的情况
            * -> 1 -> 2
                 | -- |
                 s----f
                      s
                      f

            * -> 1 -> 2 -> 3
                 | ------- |
                 s----f
                      s
                 f
                           s
                           f
     */
    public boolean hasCycle(ListNode head) {
        /*
            不存在元素 或 只有一个元素 - 不存在环
         */
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // 走一步
            slow = slow.next;
            // 走两步
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
