package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

public class StudentProfileDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthYear;
    private String organization;
    private String gender;
    private boolean stableConnection;
    private String primaryDevice;
    private boolean previouslyMentored;

    public StudentProfileDTO() {

    }

    public StudentProfileDTO(Long id, String email, String firstName, String lastName, String birthYear, String organization,
                             String gender, boolean stableConnection, String primaryDevice, boolean previouslyMentored) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.organization = organization;
        this.gender = gender;
        this.stableConnection = stableConnection;
        this.primaryDevice = primaryDevice;
        this.previouslyMentored = previouslyMentored;
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

    public boolean isStableConnection() {
        return stableConnection;
    }

    public void setStableConnection(boolean stableConnection) {
        this.stableConnection = stableConnection;
    }

    public String getPrimaryDevice() {
        return primaryDevice;
    }

    public void setPrimaryDevice(String primaryDevice) {
        this.primaryDevice = primaryDevice;
    }

    public boolean isPreviouslyMentored() {
        return previouslyMentored;
    }

    public void setPreviouslyMentored(boolean previouslyMentored) {
        this.previouslyMentored = previouslyMentored;
    }
}
