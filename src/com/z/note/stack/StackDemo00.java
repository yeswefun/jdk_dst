package com.z.note.stack;

import java.util.Iterator;
import java.util.Stack;

class StackDemo00 {

    /*
        Stack的源码内部是通过数组实现的，
        执行push() 时将元素追加到数组末尾，
        执行peek() 时返回数组末尾元素（不删除该元素），
        执行pop() 时取出数组末尾元素，并将该元素从数组中删除。

        所以 用foreach或者迭代器访问的时候，是按照数组从索引0开始的方式遍历的！！
        即：从栈底到栈顶；刚好和弹栈的方式相反！！！
        所以，涉及元素出栈的时候，万万不可用foreach或迭代器

        迭代器访问顺序:
            1 2 3
        foreach访问顺序:
            1 2 3
        弹栈访问顺序:
            3 2 1
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("迭代器访问顺序:");
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println((int) iterator.next());
        }

        System.out.println("foreach访问顺序:");
        for (int num : stack) {
            System.out.println(num);
        }

        System.out.println("弹栈访问顺序:");
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
