package com.z.mk07_heap;

import java.util.PriorityQueue;
import java.util.*;


class _347_前K个高频元素_jdk {

    private static class Freq implements Comparable<Freq> {

        int elem, cnt;

        public Freq(int elem, int cnt) {
            this.elem = elem;
            this.cnt = cnt;
        }

        /*
            频次相同，如何区分优先级高
         */
        @Override
        public int compareTo(Freq o) {
            return cnt - o.cnt;
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
            jdk 中 PriorityQueue 底层使用的是 最小堆 实现
                小的在上，大的在下
         */
        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(new Freq(key, map.get(key)));
            } else {
                // map.get(key) > min(queue)
                if (map.get(key) > queue.peek().cnt) {
                    queue.remove();
                    queue.add(new Freq(key, map.get(key)));
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.remove().elem);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
        jdk 内置的 PriorityQueue 底层使用的是 最小堆
     */
    public static void main(String[] args) throws Exception {

        _347_前K个高频元素_jdk instance = _347_前K个高频元素_jdk.class.newInstance();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ret = instance.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ret));

        int[] nums2 = {1};
        int[] ret2 = instance.topKFrequent(nums2, 1);
        System.out.println(Arrays.toString(ret2));
    }
}
