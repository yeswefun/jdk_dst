package com.z.mk02_stack;

class LinkedStackTest00 {

    public static void main(String[] args) {

        LinkedStack<Integer> stack = new LinkedStack<>();
        System.out.println(stack + ", isEmpty(): " + stack.isEmpty());
        for (int i = 0; i < 8; i++) {
            stack.push(i);
        }
        System.out.println(stack + ", isEmpty(): " + stack.isEmpty());

        while (!stack.isEmpty()) {
            System.out.println(stack.peek() + "@" + stack.pop() + "@" + stack.getSize());
        }
        System.out.println(stack + ", isEmpty(): " + stack.isEmpty());
    }
}
