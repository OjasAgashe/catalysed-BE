package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationMentorDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationOrgUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationStudentDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.UserDTO;

import java.util.List;

public interface RegistrationService {

    public List<RegistrationOrgUserDTO> getOrganizationUsers();

    public void registerOrgUser(RegistrationOrgUserDTO orgUserDTO) throws Exception;

    public void registerStudent(RegistrationStudentDTO studentDTO) throws Exception;

    public void registerMentor(RegistrationMentorDTO mentorDTO) throws Exception;

    public void registerAppUser(UserDTO userDTO) throws Exception;

//    public void registerOrgUser(OrganizationUserDTO userDTO) throws Exception;

    public void registerUser(UserDTO userDTO) throws Exception;

    public void changeUserPassword(UserDTO userDTO) throws Throwable;
}
