package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import com.ojas.gcp.firstappenginetryout.rest.dto.RegistrationOrgUserDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.UserDTO;
import com.ojas.gcp.firstappenginetryout.service.EmailServiceImpl;
import com.ojas.gcp.firstappenginetryout.service.MentorService;
import com.ojas.gcp.firstappenginetryout.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String getRegistrationPage() throws Exception {
        return ("<h1>Welcome to registration</h1>");
    }

    @PostMapping(value = "register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) throws Exception {
        //add validations
        registrationService.registerUser(userDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }


    @PutMapping(value = "changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody UserDTO userDTO) throws Throwable {
        //add validations
        registrationService.changeUserPassword(userDTO);
        return ResponseEntity.ok("Password changed Successfully");
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
    public List<Mentor > getAllMentors() {
        return mentorService.getMentors();
    }


    @PostMapping(value = "register/organization")
    public ResponseEntity<Object> registerOrgUser(@RequestBody RegistrationOrgUserDTO orgUserDTO) throws Exception {
        //add validations
        registrationService.registerOrgUser(orgUserDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }
}
