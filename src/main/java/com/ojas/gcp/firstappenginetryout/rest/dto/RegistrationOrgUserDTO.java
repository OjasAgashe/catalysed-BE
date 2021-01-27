package com.ojas.gcp.firstappenginetryout.rest.dto;

public class RegistrationOrgUserDTO extends RegistrationUserDTO {
    private String phoneNumber;

    public RegistrationOrgUserDTO() { }

    public RegistrationOrgUserDTO(String email, String password, String firstName,
                                 String lastName, String phoneNumber) {
        super(email, password, firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
