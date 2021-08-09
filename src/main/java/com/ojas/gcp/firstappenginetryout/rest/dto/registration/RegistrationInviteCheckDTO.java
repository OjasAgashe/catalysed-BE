package com.ojas.gcp.firstappenginetryout.rest.dto.registration;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

public class RegistrationInviteCheckDTO {
    private boolean validInvite;
    private String orgName;
    private String email;
    private UserType userType;

    public RegistrationInviteCheckDTO() {

    }

    public RegistrationInviteCheckDTO(boolean validInvite, String orgName, String email, UserType userType) {
        this.validInvite = validInvite;
        this.orgName = orgName;
        this.email = email;
        this.userType = userType;
    }

    public boolean isValidInvite() {
        return validInvite;
    }

    public void setValidInvite(boolean validInvite) {
        this.validInvite = validInvite;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
