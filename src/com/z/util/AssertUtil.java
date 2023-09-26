package com.z.util;

public class AssertUtil {
    public static void test(boolean b) {
        try {
            if (!b)
                throw new Exception("未通过测试");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
