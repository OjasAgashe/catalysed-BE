package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

public class MentorProfileDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthYear;
    private String organization;
    private String gender;
    private String qualification;
    private String profession;
    private boolean stableConnection;
    private boolean previouslyMentored;
    private int experience;

    public MentorProfileDTO() {
    }

    public MentorProfileDTO(Long id, String email, String firstName, String lastName, String birthYear, String organization,
                            String gender, String qualification, String profession, boolean stableConnection,
                            boolean previouslyMentored, int experience) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.organization = organization;
        this.gender = gender;
        this.qualification = qualification;
        this.profession = profession;
        this.stableConnection = stableConnection;
        this.previouslyMentored = previouslyMentored;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isStableConnection() {
        return stableConnection;
    }

    public void setStableConnection(boolean stableConnection) {
        this.stableConnection = stableConnection;
    }

    public boolean isPreviouslyMentored() {
        return previouslyMentored;
    }

    public void setPreviouslyMentored(boolean previouslyMentored) {
        this.previouslyMentored = previouslyMentored;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
