package com.example.el_partani_screens;

import android.graphics.Bitmap;

public class Student extends Person{
  private String claas;


    public Student(String name, Bitmap bitmap, String phone, String email, String claas) {
        super(name, bitmap, phone, email);
        this.claas = claas;
    }

    public Student(String name, String phone, String email, String claas) {
        super(name, phone, email);
        this.claas = claas;
    }

    public String getClaas() {
        return claas;
    }

    public void setClaas(String claas) {
        this.claas = claas;
    }
}

