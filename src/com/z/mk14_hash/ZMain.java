package com.z.mk14_hash;

import java.util.HashMap;
import java.util.HashSet;

class ZMain {

    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        int c = 0;
        System.out.println(((Integer) c).hashCode());

        double d = 2.71828;
        System.out.println(((Double) d).hashCode());

        double e = 3.1415926;
        System.out.println(((Double) e).hashCode());

        String f = "java";
        System.out.println(f.hashCode());

        User u = new User(1, "java");
        User u2 = new User(1, "JAVA");
        User u3 = new User(1, "java");
        System.out.println(u.hashCode());
        System.out.println(u2.hashCode());

        //HashSet, HashMap
        HashSet<User> set = new HashSet<>();
        set.add(u);
        set.add(u2);
        System.out.println(set);

        HashMap<User, Integer> map = new HashMap<>();
        map.put(u, 100);
        map.put(u2, 200);
        System.out.println(map);

        //去掉 User 中的 hashCode 也不会报错
        //Object#hashCode(); // memory address-> int
        System.out.println("--- hashCode");
        System.out.println(u.hashCode());
        System.out.println(u3.hashCode());
    }
}
