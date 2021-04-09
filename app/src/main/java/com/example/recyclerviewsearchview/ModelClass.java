package com.example.recyclerviewsearchview;

public class ModelClass {

    private String name;

    public ModelClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModelClass{" +
                "name='" + name + '\'' +
                '}';
    }
}
