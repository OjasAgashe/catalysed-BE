package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.User;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.UserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.RegistrationOrgUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private OrganizationUserRepository orgUserRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerOrgUser(RegistrationOrgUserDTO orgUserDTO) throws Exception {
        //check if user already present
        if (orgUserRepository.findByEmail(orgUserDTO.getEmail()).isPresent()) {
            //add custom exception
            throw new Exception("User already registered in system - Please Login");
        }
        /*
            validations
            1. Valid email-id
            2. Valid password
        */
        orgUserRepository.saveAndFlush(getUser(orgUserDTO));

        //generate Activation mail

    }

    @Override
    public void registerUser(UserDTO userDTO) throws Exception {
        //check if user already present
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            //add custom exception
            throw new Exception("User already registered in system");
        }
        userRepository.saveAndFlush(getUser(userDTO));
    }

    // Need to create an object which extends userDTO for the old password verification
    @Override
    public void changeUserPassword(UserDTO userDTO) throws Throwable {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (!user.isEmpty()) throw  new UsernameNotFoundException("User not found");

        User passwordChangeUser = user.get();
        passwordChangeUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(passwordChangeUser);
    }


    private OrganizationUser getUser(RegistrationOrgUserDTO userDTO) {
        OrganizationUser user = new OrganizationUser();
        user.setType(UserType.ORGANIZATION_USER);
        user.setEmail(userDTO.getEmail());
        //TO-DO : Validate password format
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAccountActivated(false);
        return user;
    }

    private User getUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        //TO-DO : Validate password format
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setFirstName(userDTO.getLastName());
        user.setActive(true);
        return user;
    }
}
