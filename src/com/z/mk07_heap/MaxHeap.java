package com.z.mk07_heap;

/*
最大堆

int parent(int k)
int leftChild(int k)
int rightChild(int k)

void siftUp(int k)
void siftDown(int k)

void add(E elem)
    siftUp

E findMax()
E extractMax()
    data.swap(0, data.getSize() - 1)
    siftDown()

E replace(E elem)
    replace: 取出最大元素后，放入一个新元素

    方式一:可以先 extractMax， 再 add， 两次O(logN)的操作

    方式二:可以直接将堆顶元素替换，然后Sift Down，一次O(logN)的操作
        效率高

heapify: 将任意数组整理成堆的形状
    方式一:
        将数组的元素添加到二叉堆，O(nlog(n))?

    方式二(快):
        二叉堆是一棵完全二叉树
        从最后一个非叶子节点开始，从右往左逐个节点进行 siftDown
 */
class MaxHeap<E extends Comparable<E>> {

    private Array4<E> data;

    public MaxHeap() {
        data = new Array4<>();
    }

    public MaxHeap(int capacity) {
        data = new Array4<>(capacity);
    }

    /*
        heapify: 将任意数组整理成堆的形状
            方式一:
                将数组的元素添加到二叉堆，O(nlog(n))?

            方式二(快):
                二叉堆是一棵完全二叉树
                从最后一个非叶子节点开始，从右往左逐个节点进行 siftDown

        如何定位最后一个非叶子节点的索引是多少?
            找到最后一个节点，然后计算它的父节点的索引即可

        最后一个非叶子节点 parent(data.getSize() - 1)

                                       15
                        17                          19
                13           (22)               16       28
            30      41      62

            22 -> 13 -> 19 -> 17 -> 15
     */
    public MaxHeap(E[] arr) {
        data = new Array4<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    /*
        replace: 取出最大元素后，放入一个新元素

        方式一:可以先 extractMax， 再 add， 两次O(logN)的操作

        方式二:可以直接将堆顶元素替换，然后Sift Down，一次O(logN)的操作
            效率高
     */
    public E replace(E elem) {
        E ret = findMax();
        data.set(0, elem);
        siftDown(0);
        return ret;
    }

    //向堆添加元素
    public void add(E elem) {
        data.addLast(elem);
        //确保堆满足最大堆的性质
        siftUp(data.getSize() - 1);
    }

    /*
        从最后一个元素开始一直 siftUp

        k: 目标节点的索引
        parent(k): 目标节点的父节点的索引

        k 是索引节点，表示从 索引为 k 的元素开始一直 siftUp
            siftUp 的过程会使 k 不断减小
            k == 0, 就没有 父节点，即 根节点

                   16
            8             12
        6      [9]

        9 与 8 比较, 互换值
        9 与 16 比较，不需要互换值

        注: 堆是一棵完全二叉树，所以可以使用 数组 进行存储
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(parent(k), k);
            k = parent(k);
        }
    }

    /*
        从第一个元素开始一直 siftDown

        k 是索引节点，表示从 索引为 k 的元素开始一直 siftDown
            siftUp 的过程会使 k 不断增大
            k == data.getSize()-1, 就没有 右节点

                  9
            8           [12]
        6

       9 与 12 比较，互换值

       leftChild(k), 左孩子的索引
            堆是一棵完全二叉树
            堆中一个节点可能没有右节点
            堆中一个节点如果没有 左孩子，那么肯定也没有 右孩子
     */
    private void siftDown(int k) {
        //左孩子不存在，右孩子就不可能存在;
        //只有当左孩子存在，才有可能存在右孩子;
        //完全二叉树性质
        while (leftChild(k) < data.getSize()) { // k是否存在左孩子
            // j -> maxChildIndex, 值最大的那个孩子的索引
            int j = leftChild(k);
            // 堆是一棵完全二叉树
            // j+1，同一个父节点下，右孩子的索引 = 左孩子索引+1
            // 若右子节点存在，且右子节点比左子节点的值大，更换 maxChildIndex 的值，即 j 的值
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k); // ok-1
//                j = j+1; // ok-2
            }
            // data[j]是leftChild和rightChild中的最大值
            // k对应的节点比它的左右孩子节点中最大的那一个还要大，满足堆的性质
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j); // k > 0; k < j; 因为 leftChild: 2k+1, rightChild: 2k+2
            k = j; // 索引往后，直到 k 对应的左孩子不存在
        }
    }

    /*
        取出堆中最大元素

                  <16>
            8             12
        6      [9]

                   9(将 16 替换为 9)
            8            [12]
        6      (9, 删除)
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        //确保堆满足最大堆的性质
        siftDown(0);
        return ret;
    }

    /*
        最大堆，根节点最大
        层次比较低的节点，不一定大于层次比较高的节点
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalStateException("heap is empty");
        }
        return data.get(0);
    }

    // --------------------------------------------- 三个辅助函数, start
    /*
        返回完全二叉树的数组表示中，
        一个索引所表示的元素的父亲节点的索引
        index: child index
     */
    private int parent(int index) {//parentIndex
        if (index == 0) { // index < 1
            throw new RuntimeException("index 0 doesn't has parent");
        }
        return (index - 1) / 2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    //index: parent index
    private int leftChild(int index) {//leftChildIndex
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    //index: parent index
    private int rightChild(int index) {//rightChildIndex
        return index * 2 + 2;
    }
    // --------------------------------------------- 三个辅助函数, end
}
