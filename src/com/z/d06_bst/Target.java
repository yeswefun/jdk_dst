package com.z.d06_bst;

class Target implements Comparable<Target> {

    private int id;

    public Target(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Target o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                '}';
    }
}
