package com.z.leet.d80_hash;

/*
https://leetcode.cn/problems/first-unique-character-in-a-string/

给定一个字符串，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1。

示例 1：
    输入: s = "leetcode"
    输出: 0

示例 2:
    输入: s = "loveleetcode"
    输出: 2

示例 3:
    输入: s = "aabb"
    输出: -1

提示:
    1 <= s.length <= 105
    s只包含小写字母
 */
class _387_字符串中的第一个唯一字符 {

    public static int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s;

        s = "leetcode";
        System.out.println(firstUniqChar(s));

        s = "loveleetcode";
        System.out.println(firstUniqChar(s));

        s = "aabb";
        System.out.println(firstUniqChar(s));
    }
}
