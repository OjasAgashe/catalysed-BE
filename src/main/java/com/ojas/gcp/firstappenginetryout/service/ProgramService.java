package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantsDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProgramService {
    ProgramDTO createProgram(SessionUser user, ProgramDTO programDTO) throws Exception;

    void updateProgram(SessionUser user, ProgramDTO programDTO) throws Exception;

    void updateProgramStatus(SessionUser user, Long programId) throws Exception;

    ProgramDTO getProgram(SessionUser user, Long id) throws Exception;

    List<ProgramOrgMetaDTO> getProgramsForOrg(SessionUser user, Long orgId) throws ValidationException;

    ProgramInvitationDTO createAndSendProgramInvitation(SessionUser user, Long programId, ProgramInvitationDTO invitationDTO) throws ValidationException;

    List<ProgramInvitationDTO> getProgramInvitations(SessionUser user, Long programId) throws ValidationException ;

    ProgramParticipantsDTO getProgramParticipants(SessionUser user, Long programId, UserType participantType) throws ValidationException;
    }
