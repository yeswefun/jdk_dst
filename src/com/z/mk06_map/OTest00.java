package com.z.mk06_map;

class OTest00 {

    private static String[] arr;

    static {
        String test = "my god is my dog , my dog is my god";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000000; i++) {
            sb.append(test).append(" ");
        }
        arr = sb.toString().split(" ");
    }

    private static void test(IMap<String, Integer> map) {
        long start = System.currentTimeMillis();
        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }
        System.out.println("ms: " + (System.currentTimeMillis() - start));
        System.out.println(map.getSize());
        System.out.println("*************************** key");
        System.out.println(map.get("dog"));
        System.out.println(map.get("god"));
        System.out.println(map.get("my"));
    }

    /*
        BSTMap
        LinkedListMap

        数据量小，LinkedListMap 比较快
        数据量大，BSTMap 比较快

        使用大量不同的词汇进行测试, 此处词汇较少，只有那么几个
        time
            ms: 5595
            ms: 848
            ms: 888
     */
    public static void main(String[] args) {
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
        BST<String, Integer> map2 = new BST<>();
        AVLMap<String, Integer> map3 = new AVLMap<>(); //avl之后

        test(map);
        test(map2);
        test(map3); //avl之后
    }
}
