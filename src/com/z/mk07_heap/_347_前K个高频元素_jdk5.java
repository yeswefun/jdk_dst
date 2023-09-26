package com.z.mk07_heap;

import java.util.PriorityQueue;
import java.util.*;

class _347_前K个高频元素_jdk5 {

    private static class Freq {

        int elem, cnt;

        /*
            最大堆 -> 最小堆
         */
        public Freq(int elem, int cnt) {
            this.elem = elem;
            this.cnt = cnt;
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

        //改用lambda
        //PriorityQueue<Integer>, Integer是元素
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        //改用方法引用
        //PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else {
                // map.get(key) > min(queue)
                if (map.get(key) > map.get(queue.peek())) {
                    queue.remove();
                    queue.add(key);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
        jdk 内置的 PriorityQueue 底层使用的是 最小堆
     */
    public static void main(String[] args) throws Exception {

        _347_前K个高频元素_jdk5 instance = _347_前K个高频元素_jdk5.class.newInstance();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ret = instance.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ret));

        int[] nums2 = {1};
        int[] ret2 = instance.topKFrequent(nums2, 1);
        System.out.println(Arrays.toString(ret2));
    }
}
