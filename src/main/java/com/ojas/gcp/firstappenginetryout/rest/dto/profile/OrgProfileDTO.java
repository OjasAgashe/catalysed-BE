package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

public class OrgProfileDTO {

    private String email;
    private String firstName;
    private String lastName;
    private OrgDetailsProfileDTO organizationDetails;

    public OrgProfileDTO() {

    }

    public OrgProfileDTO(String email, String firstName, String lastName, OrgDetailsProfileDTO organizationDetails) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organizationDetails = organizationDetails;
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

    public OrgDetailsProfileDTO getOrganizationDetails() {
        return organizationDetails;
    }

    public void setOrganizationDetails(OrgDetailsProfileDTO organizationDetails) {
        this.organizationDetails = organizationDetails;
    }
}
