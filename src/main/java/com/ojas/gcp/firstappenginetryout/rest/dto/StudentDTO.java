package com.ojas.gcp.firstappenginetryout.rest.dto;

import java.util.List;

public class StudentDTO {
    private Long id;
    private PersonDTO user;
    private String school;
    private String grade;
    private String memberType;
    private List<String> mentors;

    public StudentDTO() {}

    public StudentDTO(Long id, Long userId, String firstName, String lastName, String grade, String school) {
        this.id = id;
        this.user = new PersonDTO(userId, firstName, lastName);
        this.grade = grade;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDTO getUser() {
        return user;
    }

    public void setUser(PersonDTO user) {
        this.user = user;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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
