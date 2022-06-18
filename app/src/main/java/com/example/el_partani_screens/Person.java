package com.example.el_partani_screens;

import android.graphics.Bitmap;

public class Person {
    private String name;
    private Bitmap bitmap;
    private String phone;
    private String email;

    public Person() {
    }
    public Person(String name) {
        this.name = name;
    }
    public Person(String name,  Bitmap bitmap, String phone, String email) {
        this.name = name;
        this.bitmap = bitmap;
        this.phone = phone;
        this.email = email;
    }

    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
