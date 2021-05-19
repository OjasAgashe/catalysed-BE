package com.ojas.gcp.firstappenginetryout.entity;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class ProfileUserEO {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private AppUser user;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    @Column(name = "birth_year")
    private String birthYear;
    @Column(name = "organization")
    private String organization;
    @Column(name = "location_country")
    private String locationCountry;
    @Column(name = "location_region")
    private String locationRegion;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "contact_phone_country")
    private String contactPhoneCountry;
    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "primary_language")
    private String primaryLanguage;
    @Column(name = "stable_connection")
    private boolean stableConnection;
    @Column(name = "mentor_qualification")
    private String mentorQualification;
    @Column(name = "mentor_profession")
    private String mentorProfession;
    @Column(name = "previously_mentored")
    private boolean previouslyMentored;
    @Column(name = "primary_device")
    private String primaryDevice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneCountry() {
        return contactPhoneCountry;
    }

    public void setContactPhoneCountry(String contactPhoneCountry) {
        this.contactPhoneCountry = contactPhoneCountry;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public boolean isStableConnection() {
        return stableConnection;
    }

    public void setStableConnection(boolean stableConnection) {
        this.stableConnection = stableConnection;
    }

    public String getMentorQualification() {
        return mentorQualification;
    }

    public void setMentorQualification(String mentorQualification) {
        this.mentorQualification = mentorQualification;
    }

    public String getMentorProfession() {
        return mentorProfession;
    }

    public void setMentorProfession(String mentorProfession) {
        this.mentorProfession = mentorProfession;
    }

    public boolean isPreviouslyMentored() {
        return previouslyMentored;
    }

    public void setPreviouslyMentored(boolean previouslyMentored) {
        this.previouslyMentored = previouslyMentored;
    }

    public String getPrimaryDevice() {
        return primaryDevice;
    }

    public void setPrimaryDevice(String primaryDevice) {
        this.primaryDevice = primaryDevice;
    }
}
