package com.ojas.gcp.firstappenginetryout.auth;

import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;

public class AuthenticationResponse {
    private UserDTO user;
    private String jwt;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(UserDTO user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
