package com.example.el_partani_screens;

public class Metting {
    private String day;
    private String hour;
    private String key;
    public Metting() {
    }
    public Metting(String day, String hour) {
        this.day = day;
        this.hour = hour;
        this.key = "";
    }
    public Metting(String day, String hour, String key) {
        this.day = day;
        this.hour = hour;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
