package com.ojas.gcp.firstappenginetryout.entity;

import java.util.List;

public class Student extends Person{
    private String school;
    private Grade grade;
    private String memberType;
    private List<String> mentors;
    public Student(String firstName, String lastName, Grade grade, String school) {
        super(firstName, lastName);
        this.grade = grade;
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public List<String> getMentors() {
        return mentors;
    }

    public void setMentors(List<String> mentors) {
        this.mentors = mentors;
    }
}
