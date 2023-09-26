package com.z.mk07_heap;

import java.util.PriorityQueue;
import java.util.*;

class _347_前K个高频元素_jdk4 {

    private static class Freq {

        int elem, cnt;

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
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            /*
                频次相同，如何区分优先级高
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

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

        _347_前K个高频元素_jdk4 instance = _347_前K个高频元素_jdk4.class.newInstance();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ret = instance.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ret));

        int[] nums2 = {1};
        int[] ret2 = instance.topKFrequent(nums2, 1);
        System.out.println(Arrays.toString(ret2));
    }
}
