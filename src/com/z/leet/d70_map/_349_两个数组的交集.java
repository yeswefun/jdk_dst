package com.z.leet.d70_map;

import java.util.*;

/*
https://leetcode.cn/problems/intersection-of-two-arrays/

给定两个数组 nums1 和 nums2，返回 它们的交集。
输出结果中的每个元素一定是 唯一 的。我们可以不考虑输出结果的顺序。

示例 1：
    输入：nums1 = [1,2,2,1], nums2 = [2,2]
    输出：[2]

示例 2：
    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[9,4]
    解释：[4,9] 也是可通过的

提示：
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000
 */
class _349_两个数组的交集 {

    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums1) {
            set.add(i); // 完成去重
        }

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                list.add(i);
                set.remove(i);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws Exception {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};

        _349_两个数组的交集 instance = _349_两个数组的交集.class.newInstance();

        int[] arr = instance.intersection(nums1, nums2);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = instance.intersection(nums3, nums4);
        System.out.println(Arrays.toString(arr2));
    }

    /*
        我的
     */
    public int[] intersectionMap(int[] nums1, int[] nums2) {

        /*
            key: value from array
            value: appearence count of value
         */
        TreeMap<Integer, Integer> m1 = new TreeMap<>();
        for (int k : nums1) {
            if (m1.containsKey(k)) {
                m1.put(k, m1.get(k) + 1);
            } else {
                m1.put(k, 1);
            }
        }

        TreeMap<Integer, Integer> m2 = new TreeMap<>();
        for (int k : nums2) {
            if (m2.containsKey(k)) {
                m2.put(k, m2.get(k) + 1);
            } else {
                m2.put(k, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        Set<Integer> keySet = m1.keySet();
        for (Integer key : keySet) {
            if (m2.containsKey(key)) {
                list.add(key);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
