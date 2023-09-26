package com.z.d03_stack;

public class ZDemo00_Stack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        while (!stack.isEmpty()) {
            System.out.println(stack.top() + " @ " + stack.pop());
        }
    }
}
