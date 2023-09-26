package com.z.d03_stack;

public class ZDemo01_Stack {

    public static void main(String[] args) {
        Stack<String> backward = new Stack<>();
        Stack<String> forward = new Stack<>();
        backward.push("https://www.baidu.com");
        backward.push("https://www.qq.com");
        backward.push("https://www.jd.com");

        //后退
        forward.push(backward.pop());
        forward.push(backward.pop());

        //前进
        backward.push(forward.pop());

        //输入新的网址
        backward.push("https://www.taobao.com");
        while (!forward.isEmpty()) {
            forward.pop();
        }
    }
}
