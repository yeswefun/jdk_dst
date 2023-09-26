package com.z.mk10_find;

/*
数组模拟并查集
 */
class UnionFind2x implements IUnionFind {

    /*
        索引为 元素
        值  为 集合(根节点)或父节点
     */
    private int[] parent;
    // size[i]表示以i为根的集合中元素个数
    // 在进行合并操作时，size才会发生改变
    private int[] size;
    // size 最好的情况就是表示 纵向 大小, 最坏的情况是表示 横向 大小

    public UnionFind2x(int size) {
        parent = new int[size];
        this.size = new int[size];

        //默认，所有元素都处于不同的集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.size[i] = 1;
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

    /*
        基于 size 的优化
            让节点少的那棵树的根指向节点多的树的根节点
            这样可以尽可能避免树的高度增加
            1           4
                2
                    3
        --------------------- 合并方式一，两棵树的最大深度增加
                        4
                    1
                        2
                            3
        --------------------- 合并方式二，两棵树的最大深度不变
            1
        4       2
                    3
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
        //根据两个元素所在树的元素个数不同判断合并方向
        //将元素个数少的集合合并到元素个数多的集合上
        //优化: 让节点个数少的根节点指向节点个数多的根节点
        //避免树的深度增加
        if (size[pRoot] < size[qRoot]) { // size -> (期望)纵向，(可能)横向
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else { // size[pRoot] >= size[qRoot]
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }
}
