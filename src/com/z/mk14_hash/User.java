package com.z.mk14_hash;

import java.util.Objects;

class User {

    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*
        加法 - 整型溢出
     */
    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + id;
//        hash = hash * B + name.hashCode();
        hash = hash * B + name.toLowerCase().hashCode();
        return hash;
    }

    /*
        hash 冲突
        作为哈希表的键，只覆盖 hashCode 是不够的
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }
}
