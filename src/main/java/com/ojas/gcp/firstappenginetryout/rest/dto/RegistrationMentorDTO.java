package com.ojas.gcp.firstappenginetryout.rest.dto;

public class RegistrationMentorDTO extends RegistrationUserDTO {
    private Long organizationId;

    public RegistrationMentorDTO() { }

    public RegistrationMentorDTO(String email, String password, String firstName,
                                  String lastName, Long organizationId) {
        super(email, password, firstName, lastName);
        this.organizationId = organizationId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
