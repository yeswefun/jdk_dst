package com.z.mk_sum;

/*
线性结构
    数组, 连续内存
    链表, 非连续内存


树结构
    数组
        最大/小堆
            完全二叉树 - 数组
        线段树
    链表


图结构




图的分类
    向
    权

最小生成树
    有权图

拓扑排序
    有向图

最短路径
    求 有向 与 无向 最短路径


图(无向无权)的基本概念
    两点相邻
        两个顶点之间存在一条边
    点的邻边
        和一个顶点相邻的边
    路径, Path
        从一个顶点到另一个顶点所走过的路
    环, Loop
        从一个顶点经过若干条边之后又回到这个顶点

    自环边
        边的两个顶点一样
        正常: 边的两个顶点不一样

    平行边
        3 = 4

    简单图
        没有 自环边，没有 平行边 的图

    合法有效的图
        图的所有顶点不一定都存在边

    联通分量
        图中相互连接相互抵达这些顶点集合
        一个图只有一个联通分量，该图一定会有生成树
            一个联通图，一定有生成树
        一个图有多个联通分量，
            对于每个联通分量，就存在生成树
        一个图一定有生成森林
            只关注生成树





图的基本表示: 邻接矩阵

图的基本表示: 邻接表

稀疏图(常用，通常) 与 稠密图
    平均每个节点的度 和 每个节点的度最大值


整个图所占的空间
    所有链表包含节点的总数 = 边的总数 * 2
        每个链表：相应顶点的邻边的集合
        0->1, 1->0 不一样

    O(V + E) 是否可以写成 O(E)
        最小生成树: E = V - 1
        完全图: E = V*(V-1) / 2

        极端情况下, 7顶点, 一条边也没有
            O(E), E == 0, 占用 7 个顶点的空间, 不行
 */
interface Graph_00 {
}
