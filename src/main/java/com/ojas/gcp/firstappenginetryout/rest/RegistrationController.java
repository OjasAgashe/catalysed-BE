package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import com.ojas.gcp.firstappenginetryout.rest.dto.*;
import com.ojas.gcp.firstappenginetryout.service.impl.EmailServiceImpl;
import com.ojas.gcp.firstappenginetryout.service.MentorService;
import com.ojas.gcp.firstappenginetryout.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import java.util.List;

@RestController
public class RegistrationController {
    private RegistrationService registrationService;
    private EmailServiceImpl emailService;
    private MentorService mentorService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, EmailServiceImpl emailService, MentorService mentorService) {
        this.registrationService = registrationService;
        this.emailService = emailService;
        this.mentorService = mentorService;
    }

    @GetMapping(value = "register")
    public String getRegistrationPage(@AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser sessionUser = (SessionUser)authentication.getPrincipal();
        return ("<h1>Welcome to " + sessionUser.getUsername() +"registration</h1>");
    }

    @PostMapping(value = "register/organization")
    public ResponseEntity<Object> registerOrgUser(@RequestBody RegistrationOrgUserDTO userDTO) throws Exception {
        //add validations
        registrationService.registerOrgUser(userDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping(value = "register/student")
    public ResponseEntity<Object> registerStudent(@RequestBody RegistrationStudentDTO userDTO) throws Exception {
        //add validations
        registrationService.registerStudent(userDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping(value = "register/mentor")
    public ResponseEntity<Object> registerMentor(@RequestBody RegistrationMentorDTO userDTO) throws Exception {
        //add validations
        registrationService.registerMentor(userDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping(value = "student/details")
    public ResponseEntity<Object> updateStudentDetails(@RequestBody UserDTO userDTO) throws Exception {
        //add validations
        registrationService.registerAppUser(userDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PutMapping(value = "changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody UserDTO userDTO) throws Throwable {
        //add validations
        registrationService.changeUserPassword(userDTO);
        return ResponseEntity.ok("Password changed Successfully");
    }

    //temporary API for development - remove once done
    @GetMapping(value = "temp/orgUsers")
    public ResponseEntity<Object> getRegisteredOrgUsers() throws Exception {
        return ResponseEntity.ok(registrationService.getOrganizationUsers());
    }

    @GetMapping(value = "sendSimpleMail")
    public void sendWelcomeMail() {
        emailService.sendSimpleMessage("ojasagashea74@gmail.com", "Welcome mail", "Welcome Text");
    }

    @GetMapping(value = "sendMemeMail")
    public void sendWelcomeMemeMail() throws MessagingException {
        emailService.sendMemeMessage("ojasagashea74@gmail.com", "Welcome mail");
    }

    @GetMapping(value = "mentor/create")
    public void createMentor() {
        mentorService.saveMentor();
    }

    @GetMapping(value = "mentor/all")
    public List<Mentor> getAllMentors(@AuthenticationPrincipal Authentication authentication) {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        System.out.println("First NAme :" + user.getUsername());
        return mentorService.getMentors();
    }
}
