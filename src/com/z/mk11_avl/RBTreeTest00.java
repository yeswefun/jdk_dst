package com.z.mk11_avl;

class RBTreeTest00 {

    public static void main(String[] args) {
        RBTree<String, Integer> map = new RBTree<>();

        String test = "my god is my dog , my dog is my god";
        String[] arr = test.split(" ");
        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }

        System.out.println(map.getSize());

        System.out.println("*************************** key");
        System.out.println(map.get("dog"));
        System.out.println(map.get("god"));
        System.out.println(map.get("my"));
    }
}
