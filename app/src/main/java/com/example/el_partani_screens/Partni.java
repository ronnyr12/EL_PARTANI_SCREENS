package com.example.el_partani_screens;

public class Partni extends Metting{
    private String teacherName;
    private String studentName;
    private boolean status;

    public Partni(String day, String hour, String teacherName, String studentName, boolean status) {
        super(day, hour);
        this.teacherName = teacherName;
        this.studentName = studentName;
        this.status = status;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
