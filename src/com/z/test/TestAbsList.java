package com.z.test;

public abstract class TestAbsList {

    protected int size;

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("size: " + size + ", index: " + index);
        }
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("size: " + size + ", index: " + index);
        }
    }
}
