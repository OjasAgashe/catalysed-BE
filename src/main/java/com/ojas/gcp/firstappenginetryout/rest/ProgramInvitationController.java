package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationInviteCheckDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;
import java.util.UUID;

@RestController
public class ProgramInvitationController {
    private ProgramService programService;

    @Autowired
    public ProgramInvitationController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping(value = "organization/programs/{programId}/invitations")
    public ResponseEntity<Object> createProgramInvitation(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long programId,
                                                   @RequestBody ProgramInvitationDTO invitationDTO) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("Cannot create a program invitation as user is not an Org user");
        }
        programService.createAndSendProgramInvitation(user, programId, invitationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Program invitation sent");
    }

    @GetMapping(value = "organization/programs/{programId}/invitations")
    public ResponseEntity<Object> getProgramInvitations(@PathVariable Long programId,
                                                        @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramInvitationsForOrgUser(user, programId));
    }

    @GetMapping(value = "students/{studentId}/invitations")
    public ResponseEntity<Object> getProgramInvitationsForStudents(@AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramInvitationsForUser(user));
    }

    @GetMapping(value = "students/{studentId}/invitations/{invitationId}")
    public ResponseEntity<Object> getProgramInvitationDetailsForStudents(@PathVariable Long invitationId,
                                                                   @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramInvitationDetailsForUser(user, invitationId));
    }

    @PutMapping(value = "students/{studentId}/invitations/{invitationId}")
    public ResponseEntity<Object> updateProgramInvitationStatusForStudent(@PathVariable Long invitationId,  @RequestParam InvitationStatus statusChange,
                                                                         @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        programService.updateProgramInviteResponseByUser(user, invitationId,statusChange);
        return ResponseEntity.ok("Invitation status changed successfully");
    }

    @GetMapping(value = "mentors/{mentorId}/invitations")
    public ResponseEntity<Object> getProgramInvitationsForMentors(@AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramInvitationsForUser(user));
    }

    @GetMapping(value = "mentors/{mentorId}/invitations/{invitationId}")
    public ResponseEntity<Object> getProgramInvitationDetailsForMentors(@PathVariable Long invitationId,
                                                                         @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramInvitationDetailsForUser(user, invitationId));
    }

    @PutMapping(value = "mentors/{mentorId}/invitations/{invitationId}")
    public ResponseEntity<Object> updateProgramInvitationStatusForMentor(@PathVariable Long invitationId,  @RequestParam InvitationStatus statusChange,
                                                                        @AuthenticationPrincipal Authentication authentication) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        programService.updateProgramInviteResponseByUser(user, invitationId,statusChange);
        return ResponseEntity.ok("Invitation status changed successfully");
    }

    @GetMapping(value = "invite/{invitationKey}")
    public ResponseEntity<Object> verifyInvite(@PathVariable UUID invitationKey) throws ValidationException {
        RegistrationInviteCheckDTO inviteCheckDTO = programService.isValidApplicationProgramInvite(invitationKey);
        return ResponseEntity.ok(inviteCheckDTO);
    }

    @GetMapping(value = "invite/development/{emailId}")
    public ResponseEntity<Object> verifyInvite(@PathVariable String emailId) throws ValidationException {
        String inviteKey = programService.tempGetInviteKey(emailId);
        return ResponseEntity.ok(inviteKey);
    }
}
