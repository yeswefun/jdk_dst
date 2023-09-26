package com.z.leet.d70_map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/*
https://leetcode.cn/problems/intersection-of-two-arrays-ii/

给你两个整数数组 nums1 和 nums2，请你以数组形式返回两数组的交集。
返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致
（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

示例 1：
    输入：nums1 = [1,2,2,1], nums2 = [2,2]
    输出：[2,2]

示例 2:
    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[4,9]

提示：
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000

进阶：
    如果给定的数组已经排好序呢？你将如何优化你的算法？
    如果 nums1 的大小比 nums2 小，哪种方法更优？
    如果 nums2 的元素存储在磁盘上，内存是有限的，
    并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
class _350_两个数组的交集II {

    public int[] intersect(int[] nums1, int[] nums2) {
        /*
            key: value from array
            value: appearence count of value
         */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int k : nums1) {
            if (map.containsKey(k)) {
                map.put(k, map.get(k) + 1);
            } else {
                map.put(k, 1);
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                //返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致
                //（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
        使用 可重复元素集合 解决

        哈希表
            使用以下数据结构来解决相关问题
                TreeSet, TreeMap
                    基于平衡二叉树实现
                HashSet, HashMap
                    基于哈希表实现

        面试(重点): 集合 和 映射
     */
    public static void main(String[] args) throws Exception {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};

        _350_两个数组的交集II instance = _350_两个数组的交集II.class.newInstance();

        int[] arr = instance.intersect(nums1, nums2);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = instance.intersect(nums3, nums4);
        System.out.println(Arrays.toString(arr2));
    }
}
