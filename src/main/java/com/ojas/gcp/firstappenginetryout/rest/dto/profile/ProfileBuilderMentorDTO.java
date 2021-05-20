package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.rest.dto.ContactDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;

public class ProfileBuilderMentorDTO extends ProfileBuilderBaseDTO{
    private String birthYear;
    private String organization;
    private String gender;
    private String qualification;
    private String profession;
    private boolean stableConnection;
    private boolean previouslyMentored;

    public ProfileBuilderMentorDTO() {
        super();
    }

    public ProfileBuilderMentorDTO(ContactDetailsDTO contactDetails, String primaryLanguage, LocationDTO location, String birthYear,
                                   String organization, String gender, String qualification, String profession,
                                   boolean previouslyMentored, boolean stableConnection) {
        super(contactDetails, primaryLanguage, location);
        this.birthYear = birthYear;
        this.organization = organization;
        this.gender = gender;
        this.qualification = qualification;
        this.profession = profession;
        this.stableConnection = stableConnection;
        this.previouslyMentored = previouslyMentored;
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

    public boolean isPreviouslyMentored() {
        return previouslyMentored;
    }

    public void setPreviouslyMentored(boolean previouslyMentored) {
        this.previouslyMentored = previouslyMentored;
    }

    public boolean isStableConnection() {
        return stableConnection;
    }

    public void setStableConnection(boolean stableConnection) {
        this.stableConnection = stableConnection;
    }
}
