package com.example.assignment2fm;

public class GradeInfo {
    private int id;
    private String firstname;
    private String lastname;
    private String course;
    private String credits;
    private String marks;

    public GradeInfo() {
    }

    public GradeInfo(int id, String firstname, String lastname, String course, String credits, String marks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
        this.credits = credits;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
