package com.z.mk11_avl;

import java.util.Arrays;

class OTest {

    /*
        Before sort
            AVL s: 0.0027555
            BST s: 0.0016251
            RB  s: 0.0015571
            HT  s: 0.0010145

        After sort, 最差的情况
            AVL s: 0.0027218
            BST s: 0.0016199
            RB  s: 0.0015195
            HT  s: 0.0010452
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        boolean sorted = false;

        test00(sorted); //AVLTree
        System.out.println("AVL s: " + ((System.nanoTime() - start) / 1000000000.0));

        start = System.nanoTime();
        test01(sorted); //BST
        System.out.println("BST s: " + ((System.nanoTime() - start) / 1000000000.0));

        start = System.nanoTime();
        test02(sorted); //RBTree
        System.out.println("RB  s: " + ((System.nanoTime() - start) / 1000000000.0));

        start = System.nanoTime();
        test03(sorted); //HashTable
        System.out.println("HT  s: " + ((System.nanoTime() - start) / 1000000000.0));
    }

    private static void test00(boolean sorted) {
        AVLTree<String, Integer> map = new AVLTree<>();

        String test = "my god is my dog , my dog is my god";
        String[] arr = test.split(" ");
        if (sorted) {
            Arrays.sort(arr);
        }

        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }
    }

    private static void test01(boolean sorted) {
        BST<String, Integer> map = new BST<>();

        String test = "my god is my dog , my dog is my god";
        String[] arr = test.split(" ");
        if (sorted) {
            Arrays.sort(arr);
        }

        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }
    }

    private static void test02(boolean sorted) {
        RBTree<String, Integer> map = new RBTree<>();

        String test = "my god is my dog , my dog is my god";
        String[] arr = test.split(" ");
        if (sorted) {
            Arrays.sort(arr);
        }

        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }
    }

    private static void test03(boolean sorted) {
//        HashTable<String, Integer> map = new HashTable<>(131071); // improved, m affect performance
        HashTable<String, Integer> map = new HashTable<>();

        String test = "my god is my dog , my dog is my god";
        String[] arr = test.split(" ");
        if (sorted) {
            Arrays.sort(arr);
        }

        for (String s : arr) {
            if (map.contains(s)) {
                Integer value = map.get(s);
                map.set(s, value + 1);
            } else {
                map.add(s, 1);
            }
        }
    }
}
