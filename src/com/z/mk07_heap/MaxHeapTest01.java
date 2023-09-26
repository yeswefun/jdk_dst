package com.z.mk07_heap;

import java.util.Random;

class MaxHeapTest01 {

    private static void test(Integer[] arr, boolean isHeapify) {
        long start = System.currentTimeMillis();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(arr);
        } else {
            maxHeap = new MaxHeap<>();
            for (Integer i : arr) {
                maxHeap.add(i);
            }
        }
        System.out.println("------------------------ heapify: " + isHeapify + ", ms: " + (System.currentTimeMillis() - start));

        int size = arr.length;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < size; i++) {
            if (a[i - 1] < a[i]) {
                throw new IllegalStateException("i-1: " + (i - 1) + ", i: " + i);
            }
        }
    }

    /*
        heapify
     */
    public static void main(String[] args) {
        int n = 1000000;
        Random r = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(Integer.MAX_VALUE);
        }

        test(arr, false);

        test(arr, true);
    }
}
