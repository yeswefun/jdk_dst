package com.z.mk10_find;

/*
数组模拟并查集
 */
class UnionFind2yxz implements IUnionFind {

    private int[] parent;
    private int[] rank; // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind2yxz(int size) {
        parent = new int[size];
        rank = new int[size];

        //默认，所有元素都处于不同的集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /*
        优化: 路径压缩 - 最理想的情况(两层)
     */
    //查找元素p所对应的集合编号
    //索引 == 值，即为根节点
    //O(h)复杂度，h为p所处的树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p < 0 || p >= parent.length");
        }
        //optimism, isConnected && unionElem 都会有提升
        //rank的相对关系还是与原来的一样，但确实是变了
        //rank维护成本高，相对关系起作用
        if (p != parent[p]) {
            p = find(parent[p]);
        }
        return parent[p];
    }

    //查看元素p和元素q是否所属一个集合
    //O(h)复杂度，h为p所处的树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /*
        基于 rank 的优化
     */
    //合并元素p和元素q所属的集合
    //p的根节点指向q的根节点
    //O(h)复杂度，h为p所处的树的高度
    @Override
    public void unionElem(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        //根据两个元素所在树的rank不同判断合并方向
        //将rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot]) {
            /*
                原先 qRoot > pRoot 的高度，
                修改指向后，最多也是 qRoot == pRoot
                    8           16
                        6           18
                                        20
                深度没有发生改变
                        16
                     8      18
                        6       20
             */
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            /*
                8           16
                    6           18
                pRoot 可以指向 qRoot, 也可以反过来
                深度发生改变
                            16
                        8       18
                            6
             */
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
