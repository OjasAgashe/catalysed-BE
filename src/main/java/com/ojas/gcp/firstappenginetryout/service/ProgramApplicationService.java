package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.applications.*;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProgramApplicationService {
    List<OrgProgramUserApplicationDTO> getProgramApplications(SessionUser user, Long programId, UserType applicantType) throws ValidationException;

    ProgramStudentApplicationDetailsDTO getProgramStudentApplicationDetails(SessionUser user, Long applicationId) throws ValidationException;

    ProgramMentorApplicationDetailsDTO getProgramMentorApplicationDetails(SessionUser user, Long applicationId) throws ValidationException;

    OrgProgramUserApplicationDTO createProgramApplication(SessionUser user, Long programId, Long studentId, OrgProgramUserApplicationDTO applicationDTO) throws ValidationException;

    void setIsViewedByOrgOnApplication(SessionUser user, Long applicationId) throws ValidationException;

    void updateApplicationStatus(SessionUser user, Long applicationId, ProgramApplicationStatus statusChange) throws ValidationException;
//    ProgramUserApplicationDTO createMentorProgramApplication(SessionUser user, Long programId, Long mentorId, ProgramUserApplicationDTO applicationDTO);

    List<LiteProgramUserApplication> getApplicationsListForUser(SessionUser user, Long userId) throws ValidationException;

    UserProgramApplicationDTO getApplicantApplication(SessionUser user, Long userId, Long applicationId) throws ValidationException;
}
