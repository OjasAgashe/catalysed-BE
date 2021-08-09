package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.MentorProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.StudentProfileDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProfileService {
    OrgProfileDTO getOrgProfile(SessionUser user, Long orgId) throws ValidationException;

    OrgDetailsProfileDTO getOrgDetailsProfileForNonOrgUser(Long OrgId) throws  ValidationException;

    OrgProfileDTO updateOrgProfile(SessionUser user, OrgProfileDTO profileDTO,  Long orgId)  throws ValidationException;

    StudentProfileDTO updateStudentProfile(SessionUser user, StudentProfileDTO profileDTO, Long studentId)  throws ValidationException;

    MentorProfileDTO updateMentorProfile(SessionUser user, MentorProfileDTO profileDTO, Long mentorId)  throws ValidationException;

    StudentProfileDTO getStudentProfile(SessionUser user, Long studentId)  throws ValidationException;

    MentorProfileDTO getMentorProfile(SessionUser user, Long mentorId)  throws ValidationException;
}
