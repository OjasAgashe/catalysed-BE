package com.ojas.gcp.firstappenginetryout.rest.dto.directory;

import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;

import java.util.List;

public class OrgStudentDirectoryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private PhoneDTO phone;
    private LocationDTO location;
    private String primaryLanguage;
    private String birthYear;
    private String organization;
    private String gender;
    private boolean stableConnection;
    private String primaryDevice;
    private boolean previouslyMentored;
    private List<OrgUserConnectedProgramDTO> connectPrograms;

    public OrgStudentDirectoryDTO() {

    }

    public OrgStudentDirectoryDTO(Long id, String firstName, String lastName, String email, PhoneDTO phone,
                                  LocationDTO location, String primaryLanguage, String birthYear, String organization,
                                  String gender, boolean stableConnection, String primaryDevice, boolean previouslyMentored,
                                  List<OrgUserConnectedProgramDTO> connectPrograms) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.primaryLanguage = primaryLanguage;
        this.birthYear = birthYear;
        this.organization = organization;
        this.gender = gender;
        this.stableConnection = stableConnection;
        this.primaryDevice = primaryDevice;
        this.previouslyMentored = previouslyMentored;
        this.connectPrograms = connectPrograms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
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

    public List<OrgUserConnectedProgramDTO> getConnectPrograms() {
        return connectPrograms;
    }

    public void setConnectPrograms(List<OrgUserConnectedProgramDTO> connectPrograms) {
        this.connectPrograms = connectPrograms;
    }
}
