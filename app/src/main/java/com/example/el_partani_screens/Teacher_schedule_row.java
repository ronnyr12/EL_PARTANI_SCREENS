package com.example.el_partani_screens;

public class Teacher_schedule_row {
    int image,houre;
    String date;

    public Teacher_schedule_row(int image, int houre, String date) {
        this.image = image;
        this.houre = houre;
        this.date = date;
    }

    public int getHoure() {
        return houre;
    }

    public void setHoure(int houre) {
        this.houre = houre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
