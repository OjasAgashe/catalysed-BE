package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationMentorDTO extends RegistrationUserDTO {
    private Long organizationId;
    private String location;
    private String gender;

    public RegistrationMentorDTO() { }

    public RegistrationMentorDTO(String email, String password, String firstName,
                                  String lastName, Long organizationId, String location, String gender) {
        super(email, password, firstName, lastName);
        this.organizationId = organizationId;
        this.location = location;
        this.gender = gender;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
