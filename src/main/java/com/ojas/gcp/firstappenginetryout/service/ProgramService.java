package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.UserViewProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.UserViewProgramInviteMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.StudentViewProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationInviteCheckDTO;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.UUID;

public interface ProgramService {
    ProgramDTO createProgram(SessionUser user, ProgramDTO programDTO) throws Exception;

    void updateProgram(SessionUser user, ProgramDTO programDTO) throws Exception;

    void updateProgramStatus(SessionUser user, Long programId) throws Exception;

    ProgramDTO getProgram(SessionUser user, Long id) throws Exception;

    List<ProgramOrgMetaDTO> getProgramsForOrg(SessionUser user, Long orgId) throws ValidationException;

    List<ProgramOrgMetaDTO> getProgramsMetaForOrgById(Long orgId) throws ValidationException;

    List<ProgramOrgMetaDTO> getConnectedProgramsForUser(Long userId) throws ValidationException;

    ProgramDTO getConnectedProgramDetailsForUser(Long userId, Long programId, UserType userType) throws ValidationException;

    List<ProgramOrgMetaDTO> getSuggestedProgramsForUser(Long userId) throws ValidationException;

    ProgramDTO getSuggestedProgramDetailsForUser(Long userId, Long programId, UserType userType) throws ValidationException;

    ProgramInvitationDTO createAndSendProgramInvitation(SessionUser user, Long programId, ProgramInvitationDTO invitationDTO) throws ValidationException;

    List<ProgramInvitationDTO> getProgramInvitationsForOrgUser(SessionUser user, Long programId) throws ValidationException ;

    List<UserViewProgramInviteMetaDTO> getProgramInvitationsForUser(SessionUser user) throws ValidationException;

    UserViewProgramInvitationDTO getProgramInvitationDetailsForUser(SessionUser user, Long inviteId) throws ValidationException;

    void updateProgramInviteResponseByUser(SessionUser user, Long inviteId, InvitationStatus responseType) throws ValidationException;

    ProgramParticipantsDTO getProgramParticipants(SessionUser user, Long programId, UserType participantType) throws ValidationException;

    StudentViewProgramParticipantsDTO getProgramParticipantsForStudents(Long userId, Long programId, UserType userType) throws ValidationException;

    ProgramParticipantsDTO getProgramParticipantsForMentors(Long userId, Long programId, UserType userType) throws ValidationException;

    //need to move this to a independent service when application invites are also introduced
    RegistrationInviteCheckDTO isValidApplicationProgramInvite(UUID inviteKey) throws ValidationException;

    String tempGetInviteKey(String emailId) throws ValidationException;

    void updateApplicationProgramInviteAndAddProgramParticipant(String userEmail, AppUser appUser);
}
