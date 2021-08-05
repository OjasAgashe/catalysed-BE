package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.*;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.OrgUserConnectionRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProgramParticipantRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.OrgUserConnectionDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProgramsController {
    private ProgramService programService;
    private OrgUserConnectionRepository orgUserConnectionRepository;
    private ProgramParticipantRepository programParticipantRepository;

    @Autowired
    public ProgramsController(ProgramService programService, OrgUserConnectionRepository orgUserConnectionRepository,
                              ProgramParticipantRepository programParticipantRepository) {
        this.programService = programService;

        this.orgUserConnectionRepository = orgUserConnectionRepository;
        this.programParticipantRepository = programParticipantRepository;
    }

    @PostMapping(value = "program")
    public ResponseEntity<Object> createProgram(@RequestBody ProgramDTO programDTO,
                                                @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser) authentication.getPrincipal();
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
        SessionUser user = (SessionUser) authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("Invalid operation - Not a Organization user");
        }
        programService.updateProgram(user, programDTO);
        return ResponseEntity.ok("Program update successful");
    }

    @PutMapping(value = "program/{id}/publish")
    public ResponseEntity<Object> updateProgramStatus(@PathVariable Long id,
                                                      @AuthenticationPrincipal Authentication authentication) throws Exception {
        programService.updateProgramStatus((SessionUser) authentication.getPrincipal(), id);
        return ResponseEntity.ok("Program published");
    }

    @GetMapping(value = "program/{id}")
    public ResponseEntity<Object> getProgram(@PathVariable Long id,
                                             @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser) authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgram(user, id));
    }

    @GetMapping(value = "organization/{orgId}/programs")
    public ResponseEntity<Object> getProgramsForOrg(@PathVariable Long orgId, @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser) authentication.getPrincipal();
        return ResponseEntity.ok(programService.getProgramsForOrg(user, orgId));
    }

//    @GetMapping(value = "organization/programs/{programId}/participants")
//    public ResponseEntity<Object> getProgramParticipants(@PathVariable Long programId, @AuthenticationPrincipal Authentication authentication) throws Exception {
//        SessionUser user = (SessionUser) authentication.getPrincipal();
//        List<ProgramParticipant> programParticipant = programParticipantRepository.findByIdProgramId(programId);
//        if (CollectionUtils.isEmpty(programParticipant)) {
//            programParticipant = new ArrayList<>();
//        }
//        return ResponseEntity.ok(programParticipant);
//    }

//    @GetMapping(value = "organization/{orgId}/connections")
//    public ResponseEntity<Object> getOrgConnections(@PathVariable Long orgId, @AuthenticationPrincipal Authentication authentication) throws Exception {
//        SessionUser user = (SessionUser) authentication.getPrincipal();
//        List<OrgUserConnection> orgUserConnections = orgUserConnectionRepository.findByIdOrgId(orgId);
//
//        if (CollectionUtils.isEmpty(orgUserConnections)) {
//            orgUserConnections = new ArrayList<>();
//        }
//
//        List<OrgUserConnectionDTO> resultList = new ArrayList<>();
//        orgUserConnections.stream().forEach(connection -> {
//            Organization org = connection.getOrganization();
//            AppUser appUser = connection.getUser();
//            OrgUserConnectionDTO connectionDTO = new OrgUserConnectionDTO(
//                    new Organization(org.getName(), org.getDescription(), org.getWebsite(), org.getSocialMediaCode(), org.getSocialMediaLink()),
//                    new Student(appUser.getId(), "A", "VVS"),
////                    new AppUser(appUser.getEmail(), appUser.getPassword(), appUser.getFirstName(), appUser.getLastName(), appUser.getType(), appUser.isAccountActivated(), appUser.isAccountVerified(), appUser.isProfileCreated()),
//                    connection.getUserType()
//            );
//            resultList.add(connectionDTO);
//        });
//        return ResponseEntity.ok(resultList);
//    }

    @GetMapping(value = "organization/programs/{programId}/participants")
    public ResponseEntity<Object> getProgramParticipants(@PathVariable Long programId,
                                                         @RequestParam(name = "type", defaultValue = "STUDENT") UserType userType,
                                                         @AuthenticationPrincipal Authentication authentication) throws Exception {
        SessionUser user = (SessionUser) authentication.getPrincipal();
        ProgramParticipantsDTO programParticipants = programService.getProgramParticipants(user, programId, userType);
        return ResponseEntity.ok(programParticipants);
    }
}
