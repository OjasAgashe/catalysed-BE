package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderMentorDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderOrgDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderStudentDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping(value = "profile/organization")
    public ResponseEntity<Object> setOrgProfile(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestBody ProfileBuilderOrgDTO orgProfileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("Cannot create a profile as user is not a org admin");
        }
        profileService.setProfile(user, orgProfileDTO);
        return ResponseEntity.ok("Profile created successfully");
    }

    @PostMapping(value = "profile/mentor")
    public ResponseEntity<Object> setMentorProfile(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestBody ProfileBuilderMentorDTO mentorProfileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.MENTOR) {
            throw new ValidationException("Cannot create a profile as user is not a mentor");
        }
        profileService.setProfile(user, mentorProfileDTO);
        return ResponseEntity.ok("Profile created successfully");
    }

    @PostMapping(value = "profile/student")
    public ResponseEntity<Object> setStudentProfile(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestBody ProfileBuilderStudentDTO studentProfileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("Cannot create a profile as user is not a student");
        }
        profileService.setProfile(user, studentProfileDTO);
        return ResponseEntity.ok("Profile created successfully");
    }

    @GetMapping(value = "profile/organization")
    public ResponseEntity<Object> getOrgProfile(@AuthenticationPrincipal Authentication authentication) {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(profileService.getOrgProfile(user));
    }

    @GetMapping(value = "profile/mentor")
    public ResponseEntity<Object> getMentorProfile(@AuthenticationPrincipal Authentication authentication) {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(profileService.getMentorProfile(user));
    }

    @GetMapping(value = "profile/student")
    public ResponseEntity<Object> getStudentProfile(@AuthenticationPrincipal Authentication authentication) {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(profileService.getStudentProfile(user));
    }
}
