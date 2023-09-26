package com.z.mk00_arr;

import com.z.entity.User;

class Array2Test01 {

    public static void main(String[] args) {
        Array2<User> arr = new Array2<>(16);

        System.out.println("*************************** add");
        for (int i = 0; i < 8; i++) {
            arr.addLast(new User(i, "name-" + i));
        }
        printArray(arr);

        User user1 = new User(-1, "name-" + -1);
        arr.addFirst(user1);
        printArray(arr);

        User user2 = new User(-2, "name-" + -2);
        arr.addLast(user2);
        printArray(arr);

        User user666 = new User(666, "name-" + 666);
        arr.add(arr.getSize() / 2, user666);
        printArray(arr);

        System.out.println("*************************** get|set");
        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));

        User user11 = new User(-11, "name-" + -11);
        arr.set(0, user11);
        User user22 = new User(-22, "name-" + -22);
        arr.set(arr.getSize() - 1, user22);

        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));

        System.out.println("*************************** remove");
        arr.remove(0);
        arr.remove(arr.getSize() - 1);
        arr.removeElem(user666);

        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.getSize() - 1));
        printArray(arr);
    }

    /*
        泛型方法
     */
    private static <T> void printArray(Array2<T> arr) {
        System.out.println(arr);
    }
}
