package com.ojas.gcp.firstappenginetryout.rest.dto.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationStudentDTO extends RegistrationUserDTO {
    private Long organizationId;
    private String school;
    private String grade;

    public RegistrationStudentDTO() { }

    public RegistrationStudentDTO(String email, String password, String firstName,
                                  String lastName, Long organizationId, String school, String grade) {
        super(email, password, firstName, lastName);
        this.organizationId = organizationId;
        this.school = school;
        this.grade = grade;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
