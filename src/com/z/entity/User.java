package com.z.entity;

public class User {

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize: " + this);
    }

    /*
        比较条件
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User) o;
        return this.id == u.id;
    }
}
