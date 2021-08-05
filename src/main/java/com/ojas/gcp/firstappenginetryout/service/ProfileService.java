package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.MentorProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.StudentProfileDTO;

import javax.xml.bind.ValidationException;

public interface ProfileService {
    OrgProfileDTO getOrgProfile(SessionUser user, Long orgId) throws ValidationException;

    OrgProfileDTO updateOrgProfile(SessionUser user, OrgProfileDTO profileDTO,  Long orgId)  throws ValidationException;

    StudentProfileDTO updateStudentProfile(SessionUser user, StudentProfileDTO profileDTO, Long studentId)  throws ValidationException;

    MentorProfileDTO updateMentorProfile(SessionUser user, MentorProfileDTO profileDTO, Long mentorId)  throws ValidationException;

    StudentProfileDTO getStudentProfile(SessionUser user, Long studentId)  throws ValidationException;

    MentorProfileDTO getMentorProfile(SessionUser user, Long mentorId)  throws ValidationException;
}
