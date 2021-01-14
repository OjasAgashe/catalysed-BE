package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.User;
import com.ojas.gcp.firstappenginetryout.repository.UserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
