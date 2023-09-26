package com.z.mk07_heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/*
https://leetcode.cn/problems/top-k-frequent-elements/

给你一个整数数组 nums 和一个整数 k，
请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

示例 1:
    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]

示例 2:
    输入: nums = [1], k = 1
    输出: [1]

提示：
    1 <= nums.length <= 105
    k 的取值范围是 [1, 数组中不相同的元素的个数]
    题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n)，其中 n 是数组大小。
 */
class _347_前K个高频元素 {

    private static class Freq implements Comparable<Freq> {

        int elem, cnt;

        public Freq(int elem, int cnt) {
            this.elem = elem;
            this.cnt = cnt;
        }

        /*
            最大堆 模拟 最小堆
            频次相同，如何区分优先级高
         */
        @Override
        public int compareTo(Freq o) {
            if (cnt < o.cnt) {
                return 1;
            } else if (cnt > o.cnt) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        /*
            key: 元素
            value: 频次
         */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        /*
            返回其中出现频率前 k 高的元素
            此处 优先队列的实现是 小的在上，大的在下
         */
        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            if (queue.getSize() < k) {
                queue.enqueue(new Freq(key, map.get(key)));
            } else {
                // map.get(key) > min(queue)
                if (map.get(key) > queue.getFront().cnt) {
                    queue.dequeue();
                    queue.enqueue(new Freq(key, map.get(key)));
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.dequeue().elem);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws Exception {

        _347_前K个高频元素 instance = _347_前K个高频元素.class.newInstance();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ret = instance.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ret));

        int[] nums2 = {1};
        int[] ret2 = instance.topKFrequent(nums2, 1);
        System.out.println(Arrays.toString(ret2));
    }
}
