package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.rest.dto.ContactDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;

public class ProfileBuilderStudentDTO extends ProfileBuilderBaseDTO{
    private String birthYear;
    private String organization;
    private String gender;
    private boolean stableConnection;
    private String primaryDevice;
    private boolean previouslyMentored;

    public ProfileBuilderStudentDTO() {
            super();
    }

    public ProfileBuilderStudentDTO(ContactDetailsDTO contactDetails, LocationDTO location, String primaryLanguage,
                                    String birthYear, String organization, String gender, boolean stableConnection,
                                    String primaryDevice, boolean previouslyMentored) {
        super(contactDetails, primaryLanguage, location);
        this.birthYear = birthYear;
        this.organization = organization;
        this.gender = gender;
        this.stableConnection = stableConnection;
        this.primaryDevice = primaryDevice;
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
