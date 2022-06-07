package com.example.el_partani_screens;

public class Profession {
    private String name;
    private int img;

    public Profession() {
        //4 firebase
    }

    public Profession(String name) {
        this.name = name;
    }

    public Profession(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
