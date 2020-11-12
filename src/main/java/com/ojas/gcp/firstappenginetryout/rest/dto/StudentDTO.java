package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.ojas.gcp.firstappenginetryout.entity.Grade;
import java.util.List;

public class StudentDTO extends PersonDTO {
    private String school;
    private Grade grade;
    private String memberType;
    private List<String> mentors;

    public StudentDTO() {}

    public StudentDTO(String firstName, String lastName, Grade grade, String school) {
        super(firstName, lastName);
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
