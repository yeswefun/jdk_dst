package com.z.mk08_seg;

class SegmentTreeTest01 {

    public static void main(String[] args) {
        String[] nums = {"1", "2", "3", "4", "5", "6"};
        SegmentTree<String> seg = new SegmentTree<>(nums, new IMerger<String>() {
            @Override
            public String merge(String a, String b) {
                return a + "_" + b;
            }
        });
        System.out.println(seg);

        System.out.println(seg.query(0, 2));    // 1, 2, 3
        System.out.println(seg.query(2, 5));    // 3, 4, 5, 6
        System.out.println(seg.query(0, 5));    // 1, 2, 3, 4, 5, 6
    }
}
