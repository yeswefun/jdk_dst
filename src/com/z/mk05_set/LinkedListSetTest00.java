package com.z.mk05_set;

class LinkedListSetTest00 {

    public static void main(String[] args) {
        LinkedListSet<String> set = new LinkedListSet<>();
        String test = "my god is my dog, my dog is my god";
        String[] arr = test.split(" ");
        for (String s : arr) {
            set.add(s);
        }
        System.out.println(set.getSize());
    }
}
