package com.z.mk08_seg;

class SegmentTreeTest00 {

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6};
        SegmentTree<Integer> seg = new SegmentTree<>(nums, new IMerger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println(seg);

        //query: [x, y]
        System.out.println(seg.query(0, 2));    // sum(1, 2, 3)         == 6
        System.out.println(seg.query(2, 5));    // sum(3, 4, 5, 6)      == 18
        System.out.println(seg.query(0, 5));    // sum(1, 2, 3, 4, 5, 6)== 21
    }
}
