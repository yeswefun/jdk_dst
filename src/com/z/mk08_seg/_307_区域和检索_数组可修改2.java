package com.z.mk08_seg;


class _307_区域和检索_数组可修改2 {

    private static SegmentTree<Integer> seg;

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    private static class NumArray {

        public NumArray(int[] nums) {
            if (nums.length <= 0) {
                throw new IllegalArgumentException("nums is empty");
            }
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            seg = new SegmentTree<>(data, (left, right) -> left + right);
        }

        /*
            O(log(n))
         */
        public int sumRange(int left, int right) {
            return seg.query(left, right);
        }

        /*
            每次 update 的复杂度为 O(log(n))
            进行 m 次 update 那么复杂度为 m*O(log(n))

            动态更新 -> 线段树
         */
        public void update(int index, int val) {
            seg.set(index, val);
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1,2,5]
        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 2 + 5 = 8
    }
}
