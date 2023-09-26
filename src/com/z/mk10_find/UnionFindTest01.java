package com.z.mk10_find;

import java.util.Random;

class UnionFindTest01 {

    private static void test(IUnionFind uf, int m) {
        Random r = new Random();
        int size = uf.getSize();
        long start = System.currentTimeMillis();
        //进行m次合并操作
        for (int i = 0; i < m; i++) {
            int a = r.nextInt(size);
            int b = r.nextInt(size);
            uf.unionElem(a, b);
        }
        //进行m次查询操作
        for (int i = 0; i < m; i++) {
            int a = r.nextInt(size);
            int b = r.nextInt(size);
            uf.isConnected(a, b);
        }
        System.out.println("ms: " + (System.currentTimeMillis() - start));
    }

    /*
        连续空间赋值，底层 jvm 进行了优化
        UnionFind2#find并不是连续空间，而是不断索引的过程
            在 isConnected 和 unionElem 中都有调用
            m越大，树可能越深
     */
    public static void main(String[] args) {
        int m = 100000; // 100000 -> uf2 < uf1, 影响到 uf2 里面树的高度
        int size = 100000;

        UnionFind2x uf2x = new UnionFind2x(size);
        UnionFind2y uf2y = new UnionFind2y(size);
        UnionFind2yx uf2yx = new UnionFind2yx(size);
        UnionFind2yxz uf2yxz = new UnionFind2yxz(size);

        /*
            ms: 39
            ms: 24
            ms: 20
            ms: 35
         */
        test(uf2x, m);
        test(uf2y, m);
        test(uf2yx, m);
        test(uf2yxz, m);
    }
}
