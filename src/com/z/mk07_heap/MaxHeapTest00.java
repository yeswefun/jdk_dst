package com.z.mk07_heap;

import java.util.Arrays;
import java.util.Random;

class MaxHeapTest00 {

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        int n = 8;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(r.nextInt(16));
        }
        //从大到小
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        System.out.println(Arrays.toString(arr));
    }
}
