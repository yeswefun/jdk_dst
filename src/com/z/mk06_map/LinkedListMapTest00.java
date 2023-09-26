package com.z.mk06_map;

class LinkedListMapTest00 {

    public static void main(String[] args) {

        LinkedListMap<String, Integer> map = new LinkedListMap<>();

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
