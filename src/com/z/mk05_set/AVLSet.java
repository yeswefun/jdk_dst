package com.z.mk05_set;

class AVLSet<E extends Comparable<E>> implements ISet<E> {

    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public boolean contains(E elem) {
        return avl.contains(elem);
    }

    @Override
    public void add(E elem) {
        avl.add(elem, null);
    }

    @Override
    public void remove(E elem) {
        avl.remove(elem);
    }
}
