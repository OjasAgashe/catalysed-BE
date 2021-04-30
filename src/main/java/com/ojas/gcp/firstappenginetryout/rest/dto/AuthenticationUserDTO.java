package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

public class AuthenticationUserDTO {
    private Long id;
    private String email;
    private String userName;
//    private String subscriptionType;
//    private String roles;
    private boolean isActive;
    private UserType userType;

    public AuthenticationUserDTO() {
    }

    public AuthenticationUserDTO(Long id, String email, String userName,
                                 boolean isActive, UserType userType) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.isActive = isActive;
        this.userType = userType;
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
}
