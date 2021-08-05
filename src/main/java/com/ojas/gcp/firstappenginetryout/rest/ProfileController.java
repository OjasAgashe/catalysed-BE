package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.MentorProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.StudentProfileDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService profileService) {
        this.service = profileService;
    }


    @GetMapping(value = "organizations/{orgId}/profile")
    public ResponseEntity<OrgProfileDTO> getOrgProfile(@AuthenticationPrincipal Authentication authentication,
                                                @PathVariable Long orgId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        OrgProfileDTO orgProfileDTO = service.getOrgProfile(user, orgId);
        return ResponseEntity.ok(orgProfileDTO);
    }

    @PutMapping(value = "organizations/{orgId}/profile")
    public ResponseEntity<Object> updateOrgProfile(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long orgId, @RequestBody OrgProfileDTO profileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        service.updateOrgProfile(user, profileDTO, orgId);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @GetMapping(value = "students/{studentId}/profile")
    public ResponseEntity<StudentProfileDTO> getStudentProfile(@AuthenticationPrincipal Authentication authentication,
                                                               @PathVariable Long studentId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a Student");
        }
        StudentProfileDTO studentProfileDTO = service.getStudentProfile(user, studentId);
        return ResponseEntity.ok(studentProfileDTO);
    }

    @PutMapping(value = "students/{studentId}/profile")
    public ResponseEntity<Object> updateStudentProfile(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long studentId, @RequestBody StudentProfileDTO profileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a Student");
        }
        service.updateStudentProfile(user, profileDTO, studentId);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @GetMapping(value = "mentors/{mentorId}/profile")
    public ResponseEntity<Object> getMentorProfile(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long mentorId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.MENTOR) {
            throw new ValidationException("User is not a Mentor");
        }
        MentorProfileDTO mentorProfileDTO = service.getMentorProfile(user, mentorId);
        return ResponseEntity.ok(mentorProfileDTO);
    }

    @PutMapping(value = "mentors/{mentorId}/profile")
    public ResponseEntity<Object> updateMentorProfile(@AuthenticationPrincipal Authentication authentication,
                                                       @PathVariable Long mentorId, @RequestBody MentorProfileDTO profileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a Student");
        }
        service.updateMentorProfile(user, profileDTO, mentorId);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
