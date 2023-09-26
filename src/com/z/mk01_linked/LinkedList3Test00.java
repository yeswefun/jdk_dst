package com.z.mk01_linked;

class LinkedList3Test00 {

    /*
        只有 add 和 remove 需要得到 index 的前一个节点,
            从有效的前一个位置开始，即从 dummyHead 开始

        set, get, contains 都是从 dummyHead.next 开始
            从有效的位置开始，即从 dummyHead.next 开始
     */
    public static void main(String[] args) {

//        LinkedList<Integer> list = new LinkedList<>();
//        LinkedList2<Integer> list = new LinkedList2<>();
        LinkedList3<Integer> list = new LinkedList3<>();

        //add
        for (int i = 0; i < 3; i++) {
            list.addFirst(i);
        }
        System.out.println(list);

        for (int i = 0; i > -3; i--) {
            list.addLast(i);
        }
        System.out.println(list);

        list.add(list.getSize() / 2, 666);
        System.out.println(list);

        //remove
        list.remove(0);
        list.remove(list.getSize() - 1);
        System.out.println(list);

        list.remove(0);
        list.remove(list.getSize() - 1);
        System.out.println(list);

        list.remove(list.getSize() / 2);
        System.out.println(list);
    }
}
