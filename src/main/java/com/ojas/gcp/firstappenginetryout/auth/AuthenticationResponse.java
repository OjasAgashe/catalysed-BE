package com.ojas.gcp.firstappenginetryout.auth;

import com.ojas.gcp.firstappenginetryout.rest.dto.AuthenticationUserDTO;

public class AuthenticationResponse {
    private AuthenticationUserDTO user;
    private String jwt;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(AuthenticationUserDTO user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public AuthenticationUserDTO getUser() {
        return user;
    }

    public void setUser(AuthenticationUserDTO user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
