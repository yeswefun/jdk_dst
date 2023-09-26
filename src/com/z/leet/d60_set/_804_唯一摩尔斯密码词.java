package com.z.leet.d60_set;

import java.util.TreeSet;

/*
https://leetcode.cn/problems/unique-morse-code-words/

国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:
'a' 对应 ".-" ，
'b' 对应 "-..." ，
'c' 对应 "-.-." ，以此类推。

为了方便，所有 26 个英文字母的摩尔斯密码表如下：
[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]

给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。
我们将这样一个连接过程称作 单词翻译 。
对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。

示例 1：
    输入: words = ["gin", "zen", "gig", "msg"]
    输出: 2
    解释:
        各单词翻译如下:
        "gin" -> "--...-."
        "zen" -> "--...-."
        "gig" -> "--...--."
        "msg" -> "--...--."
    共有 2 种不同翻译, "--...-." 和 "--...--.".

示例 2：
    输入：words = ["a"]
    输出：1

提示：
    1 <= words.length <= 100
    1 <= words[i].length <= 12
    words[i] 由小写英文字母组成
 */
public class _804_唯一摩尔斯密码词 {

    /*
        单词 -> 摩尔斯密码密码串
        添加到 set
     */
    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        TreeSet<String> set = new TreeSet<>();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            //word -> codes
            for (int i = 0; i < word.length(); i++) {
                //words[i] 由小写英文字母组成
                sb.append(codes[word.charAt(i) - 'a']);
            }
            //去重
            set.add(sb.toString());
        }

        return set.size();
    }

    public static void main(String[] args) throws Exception {
        _804_唯一摩尔斯密码词 instance = _804_唯一摩尔斯密码词.class.newInstance();

        String[] w1 = {"gin", "zen", "gig", "msg"};
        System.out.println(instance.uniqueMorseRepresentations(w1));

        String[] w2 = {"a"};
        System.out.println(instance.uniqueMorseRepresentations(w2));
    }
}
