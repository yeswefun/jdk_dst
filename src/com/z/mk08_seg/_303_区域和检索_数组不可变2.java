package com.z.mk08_seg;

class _303_区域和检索_数组不可变2 {

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    private static class NumArray {
        /*
            sum[i]存储前i个元素和
                sum[0] = 0, 0个元素的和; 没有元素
                sum[1] 表示前 一 个元素的和; 0
                sum[2] 表示前 两 个元素的和; 0, 1
                sum[3] 表示前 三 个元素的和; 0, 1, 2
                ...
                sum[i] 存储nums[0, i-1]的和
         */
        private int[] sum;

        /*
            nums = [1, 2, 3, 4]
            sum  = [0, 0, 0, 0, 0]
            sum[0] = 0
            sum[1] = 0 + 1 = 1
            sum[2] = 1 + 2 = 3
            sum[3] = 3 + 3 = 6
            sum[4] = 6 + 3 = 9
            sum  = [0, 1, 3, 6, 9]
         */
        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int i = 1; i < sum.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }

        /*
            从 i 到 j 这个区间段的和
         */
        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

    /*
        区域数据不可变 -> 预处理
            不使用 线段树
     */
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
