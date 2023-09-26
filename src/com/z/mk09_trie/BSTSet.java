package com.z.mk09_trie;

/*
因为set底层是使用 BST 来实现的，
所以元素需要具备可比性
 */
class BSTSet<E extends Comparable<E>> implements ISet<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(E elem) {
        return bst.contains(elem);
    }

    /*
        避免添加重复元素
            BST本身具有去重功能
     */
    @Override
    public void add(E elem) {
        bst.add(elem);
    }

    @Override
    public void remove(E elem) {
        bst.remove(elem);
    }
}
