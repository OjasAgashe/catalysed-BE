package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

public class AuthenticationUserDTO {
    private Long id;
    private String email;
    private String userName;
//    private String subscriptionType;
//    private String roles;
    private String orgName;
    private Long orgId;
    private boolean isActive;
    private UserType userType;
    private boolean accountVerified;
    private boolean profileCreated;

    public AuthenticationUserDTO() {
    }

    public AuthenticationUserDTO(Long id, String email, String userName,  Long orgId, String orgName, boolean isActive,
                                 UserType userType, boolean accountVerified, boolean profileCreated) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.isActive = isActive;
        this.userType = userType;
        this.accountVerified = accountVerified;
        this.profileCreated = profileCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public boolean isProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(boolean profileCreated) {
        this.profileCreated = profileCreated;
    }
}
