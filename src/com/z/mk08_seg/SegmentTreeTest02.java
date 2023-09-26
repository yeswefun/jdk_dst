package com.z.mk08_seg;

class SegmentTreeTest02 {

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> seg = new SegmentTree<>(nums, new IMerger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                //return Math.max(a, b);    // 某个区间的最大值
                //return Math.min(a, b);    // 某个区间的最小值
                return a + b;
            }
        });
        System.out.println(seg);

        System.out.println(seg.query(0, 2));    // sum(-2, 0, 3) == 1
        System.out.println(seg.query(2, 5));    // sum(3, -5, 2, -1) == -1
        System.out.println(seg.query(0, 5));    // sum(-2, 0, 3, -5, 2, -1) == -3
    }
}
