package com.ojas.gcp.firstappenginetryout.rest.dto;

public class RegistrationStudentDTO extends RegistrationUserDTO {
    private Long organizationId;

    public RegistrationStudentDTO() { }

    public RegistrationStudentDTO(String email, String password, String firstName,
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
