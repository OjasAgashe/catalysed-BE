package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgMentorDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgStudentDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgUserDirectoryMetaDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface DirectoryService {
    List<OrgUserDirectoryMetaDTO> getOrgStudentConnections(Long orgId) throws ValidationException;

    OrgStudentDirectoryDTO getConnectedStudent(Long orgId, Long studentId) throws ValidationException;

    List<OrgUserDirectoryMetaDTO> getOrgMentorConnections(Long orgId) throws ValidationException;

    OrgMentorDirectoryDTO getConnectedMentor(Long orgId, Long mentorId) throws ValidationException;
}
