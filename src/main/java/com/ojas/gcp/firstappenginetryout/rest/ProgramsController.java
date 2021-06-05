package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;

@RestController
public class ProgramsController {
    private ProgramService programService;

    @Autowired
    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping(value = "program")
    public ResponseEntity<Object> createProgram(@RequestBody ProgramDTO programDTO,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("Invalid operation - Not a Organization user");
        }
        //add validations
        ProgramDTO createdProgramDTO = programService.createProgram(user, programDTO);
        return ResponseEntity.ok(createdProgramDTO);
    }

    @PutMapping(value = "program/{programId}")
    public ResponseEntity<Object> updateProgram(@RequestBody ProgramDTO programDTO,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("Invalid operation - Not a Organization user");
        }
        programService.updateProgram(user, programDTO);
        return ResponseEntity.ok("Program update successful");
    }

    @PutMapping(value = "program/{id}/publish")
    public ResponseEntity<Object> updateProgramStatus(@PathVariable Long id,
                                                      @AuthenticationPrincipal Authentication authentication) throws Exception {
        programService.updateProgramStatus((SessionUser)authentication.getPrincipal(), id);
        return ResponseEntity.ok("Program published");
    }

    @GetMapping(value = "program/{id}")
    public ResponseEntity<Object> getProgram(@PathVariable Long id,
                                             @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgram(user, id));
    }

    @GetMapping(value = "organization/{orgId}/programs")
    public ResponseEntity<Object> getProgramsForOrg(@PathVariable Long orgId, @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramsForOrg(user, orgId));
    }
}
