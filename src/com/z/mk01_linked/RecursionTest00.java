package com.z.mk01_linked;

class RecursionTest00 {

    private static int sum(int[] arr) {
        return sum(arr, 0);
    }

    /*
        sum: [n, arr.length)
        0 1 2 3 4 5 6 ... arr.length)
        0 [1, arr.length)
           1 [2, arr.length)
              2 [3, arr.length)
                 3 [4, arr.length)
                    n [n+1, arr.length)
     */
    public static int sum(int[] arr, int n) {
        if (n == arr.length)
            return 0;
        return arr[n] + sum(arr, n + 1);
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(sum(arr));
    }

//    private static int sum(int[] arr, int offset) {
//        System.out.println("offset: " + offset);
//        if (offset + 1 < arr.length) {
//            return arr[offset] + sum(arr, offset + 1);
//        }
//        return arr[offset];
//    }
}
