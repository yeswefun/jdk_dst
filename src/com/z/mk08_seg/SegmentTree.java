package com.z.mk08_seg;

import java.util.Arrays;

/*
private void buildSegmentTree(int treeIndex, int l, int r)

public E query(int ql, int qr)
private E query(int treeIndex, int l, int r, int ql, int qr)

public void set(int index, E elem)
private void set(int treeIndex, int l, int r, int index, E elem)


public E get(int index)
public int getSize()

private int leftChild(int index)
private int rightChild(int index)

public String toString()
 */
class SegmentTree<E> {

    /*
        存放 原来的数据
     */
    private E[] data;

    /*
        合并后的结果
        存放 线段树 的数据
     */
    private E[] tree;

    //两个区间如何合并
    private IMerger<E> merger;

    /*
        对于满二叉树，最后一层的节点数 "大致" 等于前面所有层节点之和
            1, 2, 4, 8, 16, ..., 2^(h-1)
            sum = 2^h - 1
            最后一层元素个数为 2^(h-1)
            最后一层前面的所有层的元素个数和:
                2^h - 1 - 2^(h-1)
                = 2^h - 2^h/2 - 1
                = (2*2^h - 2^h) / 2 - 1
                = 2^(h-1) - 1

        如果区间有n个元素(最后一层有n个元素)，线段树以数组表示需要有多少节点?
            最后一层为 n = 2^k
                则最后一层前面的所有层的元素个数和 == 2^k-1 == n-1, 这里多取一个，即 2^k == n
                所以最后一层及最后一层前面的所有层的元素个数总和，约为 2n
            最后一层为 n = 2^k+1
                在 n = 2^k 的情况下，多出一个
                最后一层前面所有层的元素和约为 2n
                现在因为多出一个，又因为 满二叉树，下一层元素个数 为 这层之上的所有元素个数的 将近 2倍
                所以 需要分配 2*2n 个元素空间
     */
    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr, IMerger<E> merger) {

        this.merger = merger;

        int size = arr.length;
        data = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            data[i] = arr[i];
        }

        //tree里面存放的是 特殊含义的操作(如:求和) 的结果
        //tree按照满二叉树进行分配空间
        tree = (E[]) new Object[size * 4]; //???
        buildSegmentTree(0, 0, size - 1);
    }

    /*
        在treeIndex的位置创建表示区间[l...r]的线段树

        最后一层，区间元素个数为 1，一个元素的和就是这个元素本身

        buildSegmentTree(0, 0, 2)
            treeIndex: 0
            l: 0
            r: 2

            leftTreeIndex: 1
            rightTreeIndex: 2

            mid = 0 + (2-0)/2 = 1
            leftTreeIndex, 0, 1
            rightTreeIndex, 2, 2
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        //区间长度为 1
        if (l == r) {
            // l == r => data[l] == data[r]
            //tree[treeIndex]存放的信息，就是 data[l] 或 data[r] 元素本身
            tree[treeIndex] = data[l];
            return;
        }

        //l < r
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        //区间范围
        //int mid = (l + r) / 2;    // 整型溢出
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        //在 treeIndex 的 左右子树 都创建好了之后，
        //那么就可以求出 treeIndex 对应的区间的值
        //特殊含义的操作(如:求和)
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /*
        返回区间[l, r]的值
     */
    public E query(int ql, int qr) {
        if ((ql < 0 || ql >= data.length) || (qr < 0 || qr >= data.length) || (ql > qr)) {
            throw new IllegalArgumentException("l: " + ql + ", r: " + qr);
        }
        return query(0, 0, data.length - 1, ql, qr);
    }

    /*
        在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[ql...qr]的值
     */
    private E query(int treeIndex, int l, int r, int ql, int qr) {
        if (l == ql && r == qr) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        /*
            l, ..., ..., mid, mid+1, ..., ql, qr, ..., r
            l, ..., ql, qr, ..., mid, mid+1, ..., ..., r
         */
        if (ql >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, ql, qr);
        } else if (qr <= mid) {
            return query(leftTreeIndex, l, mid, ql, qr);
        }
        /*
            l, ..., ql, ..., mid, mid+1, ..., qr, ..., r
         */
        E leftRet = query(leftTreeIndex, l, mid, ql, mid);
        E rightRet = query(rightTreeIndex, mid + 1, r, mid + 1, qr);
        return merger.merge(leftRet, rightRet);
    }

    //对一个值进行更新
    //将index位置的值，更新为elem
    public void set(int index, E elem) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index < 0 || index >= data.length");
        }
        data[index] = elem;
        set(0, 0, data.length - 1, index, elem);
    }

    /*
        在以treeIndex为根的线段树中更新index的值为elem
           从根节点找到index所对应的叶子节点，O(log(n)),
           所以更新的时间复杂度也为 O(log(n))
     */
    private void set(int treeIndex, int l, int r, int index, E elem) {
        if (l == r) {
            tree[treeIndex] = elem;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //类似二叉搜索的更新过程
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, elem);
        } else { //index <= mid
            set(leftTreeIndex, l, mid, index, elem);
        }
        //index, 及其父节点，祖父节点，... 也要做相应的变化
        //可能影响到左边，可能影响到右边，也可能两边都改变了
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //------------------------------------------------------
    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index < 0 || index >= data.length");
        }
        return data[index];
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }
}
