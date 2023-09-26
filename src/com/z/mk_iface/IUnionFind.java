package com.z.mk_iface;

/*
不考虑 添加 和 删除 元素
 */
interface IUnionFind {

    int getSize();

    // 判断两个元素是否相连
    boolean isConnected(int p, int q);

    //将两个元素合并成一个集合中的元素
    void unionElem(int p, int q);
}
