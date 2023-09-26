package com.z.mk05_set;

class BSTSetTest00 {

    public static void main(String[] args) {
        BSTSet<String> set = new BSTSet<>();
        String test = "my god is my dog, my dog is my god";
        String[] arr = test.split(" ");
        for (String s : arr) {
            set.add(s);
        }
        System.out.println(set.getSize());
    }
}
