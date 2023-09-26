package com.z.mk00_arr;

class Array0Test00 {
    /*
        数组的局限
            一开始就分配好内存
            大小固定，无法动态扩容
     */
    public static void main(String[] args) {

        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 10;
        }
        System.out.println("************************ 1");
        printArr(arr);

        System.out.println("************************ 2");
        int[] scoreArr = new int[]{10, 20, 30};
        printArr(scoreArr);

        System.out.println("************************ 3, write");
        scoreArr[1] = 66;
        printArr(scoreArr);

        System.out.println("************************ 4, read");
        System.out.println(scoreArr[1]);

        System.out.println("************************ 5, [66]");
        try {
            scoreArr[66] = -1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        System.out.println("************************ 6, [-1]");
        try {
            scoreArr[-1] = -1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static void printArr(int[] arr) {
        for (int v : arr) {
            System.out.println(v);
        }
    }
}
