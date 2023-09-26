package com.z.mk08_seg;

/*
https://leetcode.cn/problems/range-sum-query-mutable/

给你一个数组 nums ，请你完成两类查询。
    其中一类查询要求 更新 数组 nums 下标对应的值
    另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，
    其中 left <= right

实现 NumArray 类：
    NumArray(int[] nums)
        用整数数组 nums 初始化对象
    void update(int index, int val)
        将 nums[index] 的值 更新 为 val
    int sumRange(int left, int right)
        返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和
        （即，nums[left] + nums[left + 1], ..., nums[right]）

示例 1：
    输入：
        ["NumArray", "sumRange", "update", "sumRange"]
        [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
    输出：
        [null, 9, null, 8]

解释：
    NumArray numArray = new NumArray([1, 3, 5]);
    numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
    numArray.update(1, 2);   // nums = [1,2,5]
    numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8

提示：
    1 <= nums.length <= 3 * 10^4
    -100 <= nums[i] <= 100
    0 <= index < nums.length
    -100 <= val <= 100
    0 <= left <= right < nums.length
    调用 update 和 sumRange 方法次数不大于 3 * 10^4
 */
class _307_区域和检索_数组可修改 {

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(index,val);
     * int param_2 = obj.sumRange(left,right);
     */
    private static class NumArray {

        private int[] sum;

        private int[] data;

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
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
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

        /*
            每次 update 的复杂度为 O(n)
            进行 m 次 update 那么复杂度为 m*O(n)
                慢
         */
        public void update(int index, int val) {
            data[index] = val;
            for (int i = index + 1; i < sum.length; i++) {
                sum[i] = sum[i - 1] + data[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1,2,5]
        System.out.println(numArray.sumRange(0, 2)); // 返回 1 + 2 + 5 = 8
    }
}
