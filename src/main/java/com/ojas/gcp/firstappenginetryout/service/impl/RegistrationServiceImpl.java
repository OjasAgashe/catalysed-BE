package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import com.ojas.gcp.firstappenginetryout.entity.Organization;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.Student;
import com.ojas.gcp.firstappenginetryout.entity.User;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.exception.DuplicateResourceException;
import com.ojas.gcp.firstappenginetryout.repository.AppUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.MentorRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.StudentRepository;
import com.ojas.gcp.firstappenginetryout.repository.UserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationMentorDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationOrgDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationOrgUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationStudentDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.SocialMediaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.UserDTO;
import com.ojas.gcp.firstappenginetryout.service.RegistrationService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private OrganizationUserRepository orgUserRepository;
    private StudentRepository studentRepository;
    private MentorRepository mentorRepository;
    private AppUserRepository appUserRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailServiceImpl emailService;

    public RegistrationServiceImpl(OrganizationUserRepository orgUserRepository, StudentRepository studentRepository,
                                   MentorRepository mentorRepository, UserRepository userRepository,
                                   PasswordEncoder passwordEncoder, AppUserRepository appUserRepository,
                                   EmailServiceImpl emailService) {
        this.orgUserRepository = orgUserRepository;
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.emailService = emailService;
    }

    @Override
    public List<RegistrationOrgUserDTO> getOrganizationUsers() {
        return convert(orgUserRepository.findAll());
    }

    private List<RegistrationOrgUserDTO> convert(List<OrganizationUser> orgUsers) {
        return orgUsers.stream()
                .map(orgUser -> new RegistrationOrgUserDTO(
                        orgUser.getEmail(),
                        "",
                        orgUser.getFirstName(),
                        orgUser.getLastName(),
                        new RegistrationOrgDetailsDTO(
                                orgUser.getOrganization().getName(),
                                orgUser.getOrganization().getDescription(),
                                orgUser.getOrganization().getWebsite(),
                                new SocialMediaDTO(
                                        orgUser.getOrganization().getSocialMediaCode(),
                                        orgUser.getOrganization().getSocialMediaLink()
                                )
                        )
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void registerOrgUser(RegistrationOrgUserDTO orgUserDTO) throws Exception {
        //check if user already present
        if (orgUserRepository.findByEmail(orgUserDTO.getEmail()).isPresent()) {
            //add custom exception
            throw new DuplicateResourceException("User already registered in system - Please Login");
        }
        /*
            validations
            1. Valid email-id
            2. Valid password
        */
        orgUserRepository.saveAndFlush(getUser(orgUserDTO));
//        emailService.sendMemeMessage("ojasagashea74@gmail.com", "Welcome mail");
        //generate Activation mail
    }

    @Override
    public void registerStudent(RegistrationStudentDTO studentDTO) throws Exception {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already registered in system - Please Login");
        }
        studentRepository.saveAndFlush(getUser(studentDTO));
//        emailService.sendMemeMessage("ojasagashea74@gmail.com", "Welcome mail");
    }

    @Override
    public void registerMentor(RegistrationMentorDTO mentorDTO) throws Exception {
        if (mentorRepository.findByEmail(mentorDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already registered in system - Please Login");
        }
        mentorRepository.saveAndFlush(getUser(mentorDTO));
//        emailService.sendMemeMessage("ojasagashea74@gmail.com", "Welcome mail");
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

    @Override
    public void registerAppUser(UserDTO userDTO) throws Exception {
//        //check if user already present
//        if (appUserRepository.findByEmail(userDTO.getEmail()).isPresent()) {
//            //add custom exception
//            throw new Exception("User already registered in system");
//        }
//        appUserRepository.saveAndFlush(getAppUser(userDTO));
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
        setUserBaseDetails(user, userDTO);
        user.setType(UserType.ORGANIZATION_USER);
        Organization organization = getOrganization(userDTO.getOrgDetails());
        organization.setOrganizationUser(user);
        user.setOrganization(organization);
//        user.setAccountActivated(true);
        return user;
    }

    private Student getUser(RegistrationStudentDTO userDTO) {
        Student user = new Student();
        setUserBaseDetails(user, userDTO);
        user.setType(UserType.STUDENT);
//        user.setSchool("VVS");
        return user;
    }

    private Mentor getUser(RegistrationMentorDTO userDTO) {
        Mentor user = new Mentor( );
        setUserBaseDetails(user, userDTO);
        user.setType(UserType.MENTOR);
//        user.setLocation(userDTO.getLocation());
//        user.setGender(userDTO.getGender());
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
//        user.setActive(true);
        return user;
    }

    private void setUserBaseDetails(AppUser user, RegistrationUserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        //TO-DO : Validate password format
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAccountActivated(false);
    }

    private Organization getOrganization(RegistrationOrgDetailsDTO organizationDetailsDTO) {
        return new Organization(
                organizationDetailsDTO.getName(),
                organizationDetailsDTO.getDescription(),
                organizationDetailsDTO.getOrgWebsite(),
                organizationDetailsDTO.getSocialMedia().getCode(),
                organizationDetailsDTO.getSocialMedia().getLink());
    }
}
