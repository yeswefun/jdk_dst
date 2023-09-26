package com.z.mk10_find;

/*
数组模拟并查集
 */
class UnionFind1 implements IUnionFind {

    /*
        索引为 元素
        值  为 集合
     */
    private int[] elems;

    public UnionFind1(int size) {
        elems = new int[size];
        //默认，所有元素都处于不同的集合
        for (int i = 0; i < size; i++) {
            elems[i] = i;
        }
    }

    @Override
    public int getSize() {
        return elems.length;
    }

    //查找元素p所对应的集合编号，O(1)
    private int find(int p) {
        if (p < 0 || p >= elems.length) {
            throw new IllegalArgumentException("p < 0 || p >= elems.length");
        }
        return elems[p];
    }

    //查看元素p和元素q是否所属一个集合, O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素p和元素q所属的集合, O(n)
    //p = q, 将 p 集合中的元素合并到 q 集合
    @Override
    public void unionElem(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < elems.length; i++) {
            if (elems[i] == pId) {
                elems[i] = qId;
            }
        }
    }
}
