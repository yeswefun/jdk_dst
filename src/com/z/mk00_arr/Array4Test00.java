package com.z.mk00_arr;

class Array4Test00 {

    public static void main(String[] args) {
        Array4<Integer> arr = new Array4<>(); // 8

        for (int i = 0; i < 16; i++) {
            arr.addLast(i);
        }
        printArray(arr);

        for (int i = 0; i < 16; i++) {
            arr.removeLast();
        }
        printArray(arr);
    }

    /*
        泛型方法
     */
    private static <T> void printArray(Array4<T> arr) {
        System.out.println(arr);
    }
}
