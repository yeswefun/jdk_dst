package com.z.mk05_set;

class OTest00 {

    private static String[] arr;

    static {
        String tpl = "my god is my dog, my dog is my god;";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000000; i++) {
            sb.append(tpl).append(" ");
        }
        arr = sb.toString().split(" ");
    }

    private static void test(ISet<String> set) {
        long start = System.currentTimeMillis();
        for (String s : arr) {
            set.add(s);
        }
        System.out.println("ms: " + (System.currentTimeMillis() - start));
        System.out.println(set.getSize());
    }

    /*
        BSTSet
        LinkListSet

        数据量小，LinkedListSet 比较快
        数据量大，BSTSet 比较快

        time
            ms: 314
            ms: 333
            ms: 530

        因为数据中重复数据多，但词汇量少
     */
    public static void main(String[] args) {
        BSTSet<String> set = new BSTSet<>();
        LinkedListSet<String> set2 = new LinkedListSet<>();
        AVLSet<String> set3 = new AVLSet<>(); //avl之后

        test(set);
        test(set2);
        test(set3); //avl之后
    }
}
