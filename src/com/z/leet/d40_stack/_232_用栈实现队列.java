package com.z.leet.d40_stack;

import java.util.Stack;

/*
https://leetcode.cn/problems/implement-queue-using-stacks/

请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：
    void push(int x) 将元素 x 推到队列的末尾
    int pop() 从队列的开头移除并返回元素
    int peek() 返回队列开头的元素
    boolean empty() 如果队列为空，返回 true ；否则，返回 false

说明：
    你 只能 使用标准的栈操作 —— 也就是只有push to top,peek/pop from top,size, 和is empty操作是合法的。
    你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */
class _232_用栈实现队列 {

    class MyQueue {

        private Stack<Integer> inStack;
        private Stack<Integer> outStack;

        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /*
            入队
         */
        public void push(int x) {
            inStack.push(x);
        }

        /*
            出队
         */
        public int pop() {
            checkOutStack();
            return outStack.pop();
        }

        /*
            获取队头
         */
        public int peek() {
            checkOutStack();
            return outStack.peek();
        }

        /*
            是否为空
         */
        public boolean empty() {
            return outStack.isEmpty() && inStack.isEmpty();
        }

        private void checkOutStack() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

    /*
        准备2个栈: inStack、 outStack
        入队时，push到inStack中
        出队时
            如果outStack为空，将inStack所有元素逐一 弹出，push到outStack， outStack弹出栈顶元素
            如果outStack不为空， outStack弹出栈顶元素
     */
    public static void main(String[] args) {

    }
}
