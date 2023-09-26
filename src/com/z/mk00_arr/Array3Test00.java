package com.z.mk00_arr;

class Array3Test00 {

    public static void main(String[] args) {
        Array3<Integer> arr = new Array3<>(); // 8

        for (int i = 0; i < 16; i++) {
            arr.addLast(i);
        }
        printArray(arr);

        for (int i = 0; i < 10; i++) {
            arr.removeLast();
        }
        printArray(arr);
    }

    /*
        泛型方法
     */
    private static <T> void printArray(Array3<T> arr) {
        System.out.println(arr);
    }
}
