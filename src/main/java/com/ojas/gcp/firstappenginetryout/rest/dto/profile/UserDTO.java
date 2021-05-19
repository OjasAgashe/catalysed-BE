package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String subscriptionType;
    private String password;
    private boolean isActive;
    private String roles;
    private UserType userType;

    public UserDTO() {}

    public UserDTO(Long id, String email, String password, String userName, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDTO(Long id, String userName, String email, UserType userType, boolean isActive) {
        this.userName = userName;
        this.email = email;
        this.userType = userType;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
