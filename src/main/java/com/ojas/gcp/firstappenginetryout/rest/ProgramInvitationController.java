package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

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
        return ResponseEntity.ok(programService.getProgramInvitations(user, programId));
    }
}
