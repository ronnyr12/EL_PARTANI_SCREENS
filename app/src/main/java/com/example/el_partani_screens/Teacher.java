package com.example.el_partani_screens;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

public class Teacher  extends Person{
    private String subject;

    public Teacher(String name, Bitmap bitmap, String phone, String email, String subject) {
        super(name, bitmap, phone, email);
        this.subject = subject;
    }

    public Teacher(String name, String phone, String email, String subject) {
        super(name, phone, email);
        this.subject = subject;
    }

    public Teacher(String name, String phone, String email) {
        super(name, phone, email);
        //this.subject = subject;
    }
    public Teacher(String name) {
        super(name);
        //this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}