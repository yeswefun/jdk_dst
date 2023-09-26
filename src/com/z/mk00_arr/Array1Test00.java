package com.z.mk00_arr;

class Array1Test00 {

    public static void main(String[] args) {
        Array1 arr = new Array1(16);

        System.out.println("*************************** add");
        for (int i = 0; i < 8; i++) {
            arr.addLast(i);
        }
        printArray(arr);

        arr.addFirst(-1);
        printArray(arr);

        arr.addLast(-2);
        printArray(arr);

        arr.add(arr.getSize() / 2, 666);
        printArray(arr);

        System.out.println("*************************** get|set");
        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));

        arr.set(0, -11);
        arr.set(arr.getSize() - 1, -22);

        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));

        System.out.println("*************************** remove");
        arr.remove(0);
        arr.remove(arr.getSize() - 1);
        arr.removeElem(666);

        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));
    }

    private static void printArray(Array1 arr) {
        System.out.println(arr);
    }
}
