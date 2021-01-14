package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;

public interface RegistrationService {
    public void registerUser(UserDTO userDTO) throws Exception;

    public void changeUserPassword(UserDTO userDTO) throws Throwable;
}
