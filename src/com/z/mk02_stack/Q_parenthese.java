package com.z.mk02_stack;

class Q_parenthese {

    private static ArrayStack<Character> stack;

    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return false;
        }
        stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[' || c == '<') {
                stack.push(c);
            } else if (c == '}' || c == ')' || c == ']' || c == '>') {
                Character c2 = stack.pop();
                if ((c == '}' && c2 != '{')
                        || (c == ')' && c2 != '(')
                        || (c == ']' && c2 != '[')
                        || (c == '>' && c2 != '<')
                ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("*********************************** 1");
        System.out.println(isValid("{a}"));
        System.out.println(isValid("{}"));
        System.out.println(isValid("{()}"));
        System.out.println(isValid("{([])}"));
        System.out.println(isValid("{([<>])}"));
        System.out.println("*********************************** 2");
        System.out.println(isValid(""));
        System.out.println(isValid("{"));
        System.out.println(isValid("{("));
        System.out.println(isValid("{(["));
        System.out.println(isValid("{([<"));
    }
}
