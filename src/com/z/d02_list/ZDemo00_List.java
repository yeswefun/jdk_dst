package com.z.d02_list;

/*
    时间复杂度(查，删，改，查)
 */
public class ZDemo00_List {

    /*
        TODO: 完善测试
     */
    private static void test(List<Integer> list) {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(0, -1);
        list.add(list.size(), -2);
        System.out.println(list);

        list.remove(0);
        list.remove(list.size() - 1);
        System.out.println(list);
        System.gc();
    }

    public static void main(String[] args) {

//        testList2();
//        testList3();

//        test(new ArrayList<>());
//        test(new ArrayList2<>());
//        test(new LinkedList<>());
//        test(new LinkedList2<>());
        test(new DLinkedList<>());
//        test(new CircleLinkedList<>());
//        test(new DCircleLinkedList<>());
    }

    private static void testList3() {
        List<Integer> list = new ArrayList2<>();
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        for (int i = 0; i < 12; i++) {
            list.remove(0);
        }
    }
}
