package com.ojas.gcp.firstappenginetryout.rest.dto.registration;

public class RegistrationOrgUserDTO extends RegistrationUserDTO {
    private RegistrationOrgDetailsDTO orgDetails;

    public RegistrationOrgUserDTO() { }

    public RegistrationOrgUserDTO(String email, String password, String firstName,
                                 String lastName, RegistrationOrgDetailsDTO orgDetails) {
        super(email, password, firstName, lastName);
        this.orgDetails = orgDetails;
    }

    public RegistrationOrgDetailsDTO getOrgDetails() {
        return orgDetails;
    }

    public void setOrgDetails(RegistrationOrgDetailsDTO orgDetails) {
        this.orgDetails = orgDetails;
    }
}
