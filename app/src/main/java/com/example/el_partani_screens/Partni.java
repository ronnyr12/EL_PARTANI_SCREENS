package com.example.el_partani_screens;

public class Partni extends Metting{
    private String teacherName;
    private String studentName;

    private String added_date;
    private String teacher_phone;
    public Partni() {
    }

    public Partni(String day, String hour, String teacherName, String studentName, String added_date,
                  String teacher_phone) {
        super(day, hour);
        this.teacherName = teacherName;
        this.studentName = studentName;
        this.added_date = added_date;
        this.teacher_phone = teacher_phone;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public void setTeacher_phone(String teacher_phone) {
        this.teacher_phone = teacher_phone;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
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


}
