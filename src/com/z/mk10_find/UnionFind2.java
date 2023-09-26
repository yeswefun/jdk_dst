package com.z.mk10_find;

/*
数组模拟并查集
 */
class UnionFind2 implements IUnionFind {

    /*
        索引为 元素
        值  为 集合(根节点)或父节点
     */
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        //默认，所有元素都处于不同的集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //查找元素p所对应的集合编号
    //索引 == 值，即为根节点
    //O(h)复杂度，h为p所处的树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p < 0 || p >= parent.length");
        }
        /*
            初始状
                0 1 2 3 4 5 6
                0 1 2 3 4 5 6
            变化了
                0 1 2 3 4 5 6
                0 1 2 1 4 5 3
                6 -> 3 -> 1 (1 所处的集合 1)

            index == parent[index]
                parent[index], 是一个根节点，集合
            index != parent[index]
                parent[index], 是一个父节点，不是一个集合
            注: 父节点不一定是根节点，根节点表示一个集合，而父节点不一定能表示一个集合
         */
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    //查看元素p和元素q是否所属一个集合
    //O(h)复杂度，h为p所处的树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素p和元素q所属的集合
    //p的根节点指向q的根节点
    //O(h)复杂度，h为p所处的树的高度
    //p = q, 将 p 集合中的元素合并到 q 集合
    @Override
    public void unionElem(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }
}
