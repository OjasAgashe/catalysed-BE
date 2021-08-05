package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.applications.*;
import com.ojas.gcp.firstappenginetryout.service.ProgramApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class ProgramApplicationController {
    private final ProgramApplicationService service;

    public ProgramApplicationController(ProgramApplicationService service) {
        this.service = service;
    }

    @GetMapping(value = "organizations/{orgId}/programs/{programId}/applications")
    public ResponseEntity<Object> getProgramApplications(@AuthenticationPrincipal Authentication authentication,
                                                         @PathVariable Long orgId, @PathVariable Long programId,
                                                         @RequestParam UserType applicantType) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        List<OrgProgramUserApplicationDTO> applicationDTOList = service.getProgramApplications(user, programId, applicantType);
        return ResponseEntity.ok(applicationDTOList);
    }

    @GetMapping(value = "organizations/{orgId}/programs/{programId}/mentor/applications/{applicationId}")
    public ResponseEntity<Object> getProgramMentorApplicationDetails(@AuthenticationPrincipal Authentication authentication,
                                                                     @PathVariable Long orgId, @PathVariable Long programId,
                                                                     @PathVariable Long applicationId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        ProgramMentorApplicationDetailsDTO applicationDetails = service.getProgramMentorApplicationDetails(user, applicationId);
        return ResponseEntity.ok(applicationDetails);
    }

    @GetMapping(value = "organizations/{orgId}/programs/{programId}/student/applications/{applicationId}")
    public ResponseEntity<Object> getProgramStudentApplicationDetails(@AuthenticationPrincipal Authentication authentication,
                                                                      @PathVariable Long orgId, @PathVariable Long programId,
                                                                      @PathVariable Long applicationId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        ProgramStudentApplicationDetailsDTO applicationDetails = service.getProgramStudentApplicationDetails(user, applicationId);
        return ResponseEntity.ok(applicationDetails);
    }

    @PutMapping(value = "organizations/{orgId}/programs/{programId}/applications/{applicationId}/update")
    public ResponseEntity<Object> updateApplicationStatus(@AuthenticationPrincipal Authentication authentication,
                                                          @PathVariable Long orgId, @PathVariable Long programId,
                                                          @PathVariable Long applicationId,
                                                          @RequestParam(name="status") ProgramApplicationStatus status) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        service.updateApplicationStatus(user, applicationId, status);
        return ResponseEntity.ok("Application status changed successfully");
    }

    @PutMapping(value = "organizations/{orgId}/programs/{programId}/applications/{applicationId}/viewed")
    public ResponseEntity<Object> setApplicationViewedProperty(@AuthenticationPrincipal Authentication authentication,
                                                               @PathVariable Long orgId, @PathVariable Long programId,
                                                               @PathVariable Long applicationId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        service.setIsViewedByOrgOnApplication(user, applicationId);
        return ResponseEntity.ok("Application viewed property set");
    }

    @PostMapping(value = "students/{studentId}/programs/{programId}/applications")
    public ResponseEntity<Object> createStudentProgramApplication(@AuthenticationPrincipal Authentication authentication,
                                                                  @PathVariable Long studentId, @PathVariable Long programId,
                                                                  @RequestBody OrgProgramUserApplicationDTO applicationDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a student");
        }
        OrgProgramUserApplicationDTO result = service.createProgramApplication(user, programId, studentId, applicationDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "mentors/{mentorId}/programs/{programId}/applications")
    public ResponseEntity<Object> createMentorProgramApplication(@AuthenticationPrincipal Authentication authentication,
                                                                 @PathVariable Long mentorId, @PathVariable Long programId,
                                                                 @RequestBody OrgProgramUserApplicationDTO applicationDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.MENTOR) {
            throw new ValidationException("User is not a mentor");
        }
        OrgProgramUserApplicationDTO result = service.createProgramApplication(user, programId, mentorId, applicationDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "students/{studentId}/applications")
    public ResponseEntity<Object> getApplicationsForStudent(@AuthenticationPrincipal Authentication authentication,
                                                           @PathVariable Long studentId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a Student");
        }
        List<LiteProgramUserApplication> result = service.getApplicationsListForUser(user, studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "students/{studentId}/applications/{applicationId}")
    public ResponseEntity<Object> getStudentApplication(@AuthenticationPrincipal Authentication authentication,
                                                            @PathVariable Long studentId,
                                                            @PathVariable Long applicationId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.STUDENT) {
            throw new ValidationException("User is not a Student");
        }
        UserProgramApplicationDTO result = service.getApplicantApplication(user, studentId, applicationId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "mentors/{mentorId}/applications")
    public ResponseEntity<Object> getApplicationsForMentor(@AuthenticationPrincipal Authentication authentication,
                                                                 @PathVariable Long mentorId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.MENTOR) {
            throw new ValidationException("User is not a mentor");
        }
        List<LiteProgramUserApplication> result = service.getApplicationsListForUser(user, mentorId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "mentors/{mentorId}/applications/{applicationId}")
    public ResponseEntity<Object> getMentorApplication(@AuthenticationPrincipal Authentication authentication,
                                                           @PathVariable Long mentorId,
                                                           @PathVariable Long applicationId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.MENTOR) {
            throw new ValidationException("User is not a mentor");
        }
        UserProgramApplicationDTO result = service.getApplicantApplication(user, mentorId, applicationId);
        return ResponseEntity.ok(result);
    }
}
