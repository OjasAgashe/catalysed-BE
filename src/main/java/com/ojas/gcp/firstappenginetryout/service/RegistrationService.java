package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.RegistrationOrgUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;

public interface RegistrationService {
    public void registerOrgUser(RegistrationOrgUserDTO orgUserDTO) throws Exception;

    public void registerAppUser(UserDTO userDTO) throws Exception;

//    public void registerOrgUser(OrganizationUserDTO userDTO) throws Exception;

    public void registerUser(UserDTO userDTO) throws Exception;

    public void changeUserPassword(UserDTO userDTO) throws Throwable;
}
