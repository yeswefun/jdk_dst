package com.z.mk11_avl;

class AVLTreeTest00 {

    public static void main(String[] args) {
        AVLTree<String, Integer> map = new AVLTree<>();

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

        System.out.println("*************************** get");
        System.out.println(map.get("dog"));
        System.out.println(map.get("god"));
        System.out.println(map.get("my"));
        System.out.println("isBST: " + map.isBST());
        System.out.println("isBalanced: " + map.isBalanced());

        for (String s : arr) {
            System.out.println("*************************** remove: [" + s + "]");
            System.out.println(map.remove(s));
            System.out.println("isBST: " + map.isBST());
            System.out.println("isBalanced: " + map.isBalanced());
        }
    }
}
