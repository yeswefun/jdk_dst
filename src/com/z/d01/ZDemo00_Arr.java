package com.z.d01;

import com.z.entity.User;

public class ZDemo00_Arr {

    public static void main(String[] args) {
        //test00();
        test01();
    }

    /*
        泛型参数只能是引用类型
     */
    public static void test01() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        ArrayList<User> list2 = new ArrayList<>();
        list2.add(new User(1, "Java"));
        list2.add(new User(1, "kotlin"));
        list2.add(new User(1, "C"));
        list2.add(new User(1, "C++"));
        System.out.println(list2);

        list2.clear();
        //提醒JVM进行垃圾回收，尝试注释掉 clear 的 for
        System.gc();
    }

    public static void test00() {
        ArrayListOfInt list = new ArrayListOfInt();
        //list.get(0);
        list.add(11);
        list.add(22);
        System.out.println("--- 2");
        list.add(33);
        list.add(44);
        System.out.println("--- 4");
        list.add(55);

        list.remove(3);
        System.out.println(list);
        list.add(3, 88);
        System.out.println(list);
        list.add(0, -1);
        System.out.println(list.get(0));
        System.out.println(list.get(list.size()-1));

        //com.z.d01.ArrayList@7f31245a
        //System.out.println(list);
        System.out.println(list);
    }
}
