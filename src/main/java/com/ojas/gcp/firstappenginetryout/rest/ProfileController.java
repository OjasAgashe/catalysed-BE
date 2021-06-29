package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.ValidationException;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService profileService) {
        this.service = profileService;
    }


    @GetMapping(value = "organization/{orgId}/profile")
    public ResponseEntity<OrgProfileDTO> getOrgProfile(@AuthenticationPrincipal Authentication authentication,
                                                @PathVariable Long orgId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        OrgProfileDTO orgProfileDTO = service.getOrgProfile(user, orgId);
        return ResponseEntity.ok(orgProfileDTO);
    }

    @PutMapping(value = "organization/{orgId}/profile")
    public ResponseEntity<Object> updateOrgProfile(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long orgId, @RequestBody OrgProfileDTO profileDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        service.updateOrgProfile(user, profileDTO, orgId);
        return ResponseEntity.ok("Profile updated successfully");
    }


//    @GetMapping(value = "mentor/{mentorId}/profile")
//    public ResponseEntity<Object> getMentorProfile(@AuthenticationPrincipal Authentication authentication) throws ValidationException {
//        SessionUser user = (SessionUser)authentication.getPrincipal();
//        if (user.getUserType() != UserType.MENTOR) {
//            throw new ValidationException("User is not an org admin");
//        }
//        return ResponseEntity.ok("Profile created successfully");
//    }
//
//    @GetMapping(value = "student/{studentId}/profile")
//    public ResponseEntity<Object> getStudentProfile(@AuthenticationPrincipal Authentication authentication) throws ValidationException {
//        SessionUser user = (SessionUser)authentication.getPrincipal();
//        if (user.getUserType() != UserType.ORGANIZATION_USER) {
//            throw new ValidationException("User is not an org admin");
//        }
//        return ResponseEntity.ok("Profile created successfully");
//    }

    //GET & Update profile for org, students and Mentor

}
