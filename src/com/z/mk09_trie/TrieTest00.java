package com.z.mk09_trie;

class TrieTest00 {

    private static String[] words;

    static {
        String tpl = "my god is my dog, my dog is my god;";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(tpl).append(" ");
        }
        words = sb.toString().split(" ");
    }

    private static void test00() {
        long start = System.currentTimeMillis();

        BSTSet<String> set = new BSTSet<>();
        for (String word : words) {
            set.add(word);
        }
        for (String word : words) {
            set.contains(word);
        }

        System.out.println("ms: " + (System.currentTimeMillis() - start));
        System.out.println(set.getSize());
    }

    private static void test01() {
        long start = System.currentTimeMillis();

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        for (String word : words) {
            trie.contains(word);
        }

        System.out.println("ms: " + (System.currentTimeMillis() - start));
        System.out.println(trie.getSize());
    }

    /*
        BSTSet
        Trie
     */
    public static void main(String[] args) {
        test00();
        test01();
    }
}
