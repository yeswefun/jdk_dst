package com.z.leet.d40_stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
https://leetcode.cn/problems/valid-parentheses/

给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。


示例 1：

输入：s = "()"
输出：true
示例2：

输入：s = "()[]{}"
输出：true
示例3：

输入：s = "(]"
输出：false
示例4：

输入：s = "([)]"
输出：false
示例5：

输入：s = "{[]}"
输出：true


提示：

1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
 */
class _20_有效的括号 {

    private static final Map<Character, Character> pair;

    static {
        pair = new HashMap<>();
        pair.put('(', ')');
        pair.put('{', '}');
        pair.put('[', ']');
        pair.put('<', '>');
    }

    /*
        左字符: (，{，[，<
        右字符: )，}，]，>
        遇到左字符入栈，遇到右字符出栈，判断是否匹配，
            不匹配，结束，无效
            匹配继续同样的操作
                直到字符串遍历完成，栈为空，则为有效
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (pair.containsKey(c)) { // 左
                stack.push(c);
            } else { // 右
                if (stack.isEmpty())
                    return false;
                Character c2 = stack.pop();
                if (c != pair.get(c2))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    /*
        System.out.println((int) '('); // 40
        System.out.println((int) ')'); // 41
        System.out.println((int) '{'); // 123
        System.out.println((int) '}'); // 125
        System.out.println((int) '['); // 91
        System.out.println((int) ']'); // 93
        System.out.println((int) '<'); // 60
        System.out.println((int) '>'); // 62

        String[] a = {};
        System.out.println(a.length);   // 数组的 length 是属性

        String s = "";
        System.out.println(s.length()); // 字符串的 length 是方法

        泛型不能使用基本类型，要使用基本类型的包装类型，即
            //Stack<char> stack = new Stack<>(); // err
            Stack<Character> stack = new Stack<>(); // ok
     */
    public static void main(String[] args) {
        System.out.println(isValid("()"));      // true
        System.out.println(isValid("()[]{}"));  // true
        System.out.println(isValid("(]"));      // false
        System.out.println(isValid("{[]}"));    // true
    }
/*
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[' || c == '<') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                Character c2 = stack.pop();
                if ((c == ')' && c2 != '(')
                        || (c == '}' && c2 != '{')
                        || (c == ']' && c2 != '[')
                        || (c == '>' && c2 != '<')
                ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
 */
/*
    public static boolean isValid(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]") || s.contains("<>")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("<>", "");
        }
        return s.isEmpty();
    }
 */
}
