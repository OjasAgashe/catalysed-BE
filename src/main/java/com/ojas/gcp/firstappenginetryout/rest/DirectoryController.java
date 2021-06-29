package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgMentorDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgStudentDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgUserDirectoryMetaDTO;
import com.ojas.gcp.firstappenginetryout.service.DirectoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class DirectoryController {
    private final DirectoryService service;

    public DirectoryController(DirectoryService service) {
        this.service = service;
    }

    @GetMapping(value = "organization/{orgId}/connections/students")
    public ResponseEntity<Object> getOrgStudentConnections(@AuthenticationPrincipal Authentication authentication,
                                                           @PathVariable Long orgId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        List<OrgUserDirectoryMetaDTO> studentMeta = service.getOrgStudentConnections(orgId);
        return ResponseEntity.ok(studentMeta);
    }

    @GetMapping(value = "organization/{orgId}/connections/students/{studentId}")
    public ResponseEntity<Object> getConnectedStudent(@AuthenticationPrincipal Authentication authentication,
                                                      @PathVariable Long orgId, @PathVariable Long studentId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        OrgStudentDirectoryDTO studentConnection = service.getConnectedStudent(orgId, studentId);
        return ResponseEntity.ok(studentConnection);
    }


    @GetMapping(value = "organization/{orgId}/connections/mentors")
    public ResponseEntity<Object> getOrgMentorConnections(@AuthenticationPrincipal Authentication authentication,
                                                          @PathVariable Long orgId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        List<OrgUserDirectoryMetaDTO> mentorMeta = service.getOrgMentorConnections(orgId);
        return ResponseEntity.ok(mentorMeta);
    }

    @GetMapping(value = "organization/{orgId}/connections/mentors/{mentorId}")
    public ResponseEntity<Object> getConnectedMentor(@AuthenticationPrincipal Authentication authentication,
                                                     @PathVariable Long orgId, @PathVariable Long mentorId) throws ValidationException {
        SessionUser user = (SessionUser)authentication.getPrincipal();
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User is not an org admin");
        }
        OrgMentorDirectoryDTO mentorConnection = service.getConnectedMentor(orgId, mentorId);
        return ResponseEntity.ok(mentorConnection);
    }
}
