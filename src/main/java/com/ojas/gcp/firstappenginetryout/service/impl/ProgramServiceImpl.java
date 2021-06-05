package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.*;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import com.ojas.gcp.firstappenginetryout.exception.BadRequestBody;
import com.ojas.gcp.firstappenginetryout.exception.DuplicateResourceException;
import com.ojas.gcp.firstappenginetryout.exception.ResourceNotFoundException;
import com.ojas.gcp.firstappenginetryout.repository.*;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import com.ojas.gcp.firstappenginetryout.service.helper.ProgramsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final OrganizationUserRepository orgUserRepository;
    private final OrganizationRepository orgRepository;
    private final AppUserRepository appUserRepository;
    private final ProgramInvitationsRepository programInvitationRepository;
    private final ProgramsHelper helper;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository, OrganizationUserRepository orgUserRepository,
                              OrganizationRepository orgRepository, ProgramsHelper helper, AppUserRepository appUserRepository,
                              ProgramInvitationsRepository programInvitationRepository) {
        this.programRepository = programRepository;
        this.orgUserRepository = orgUserRepository;
        this.orgRepository = orgRepository;
        this.appUserRepository = appUserRepository;
        this.programInvitationRepository = programInvitationRepository;
        this.helper = helper;
    }

    @Override
    public ProgramDTO createProgram(SessionUser user, ProgramDTO programDTO) throws Exception {
        //push to separate validation methods
        if (programDTO.getId() != null) {
            Optional<Program> dbRecordById = programRepository.findById(programDTO.getId());
            if (dbRecordById.isPresent()) {
                throw new Exception("Program with Id already present");
            }
        }
        OrganizationUser sessionOrgUser =getSessionOrgUser(user);

        Optional<Program> dbRecordByTitle = programRepository.findByTitle(programDTO.getTitle());
        if (dbRecordByTitle.isPresent() && dbRecordByTitle.get().getOrganization().getId().equals(sessionOrgUser.getOrganization().getId())) {
            throw new DuplicateResourceException("Another Program exists with the same Title, please change the title");
        }
        Program program = helper.buildProgram(programDTO);
        program.setOrganization(sessionOrgUser.getOrganization());
        programRepository.saveAndFlush(program);
        return helper.buildProgramDTO(program);
    }


    @Override
    public void updateProgram(SessionUser user, ProgramDTO programDTO) throws Exception {
        if (programDTO.getId() == null) {
            throw new BadRequestBody("program Id not present in request");
        }

        Program program = getProgramRecord(programDTO.getId());
        validateSessionUserOrgAccess(user, program.getOrganization().getId());
        helper.setProgramDetails(programDTO, program);
        programRepository.saveAndFlush(program);
//        return buildProgramDTO(program);
    }

    @Override
    public void updateProgramStatus(SessionUser user, Long programId) throws Exception {
        Program program = getProgramRecord(programId);
        validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is already published");
        }
        program.setStatus(ProgramStatus.PUBLISHED);
        programRepository.saveAndFlush(program);
    }

    @Override
    public ProgramDTO getProgram(SessionUser user, Long id) throws Exception {
        Program program = getProgramRecord(id);
        validateSessionUserOrgAccess(user, program.getOrganization().getId());
        return helper.buildProgramDTO(program);
    }

    @Override
    public List<ProgramOrgMetaDTO> getProgramsForOrg(SessionUser user, Long orgId) throws ValidationException {
        //TO_DO :  findAllByOrgId
        Optional<Organization> org = orgRepository.findById(orgId);
        if(!org.isPresent()) {
            throw new ValidationException("Invalid org Id");
        }
        validateSessionUserOrgAccess(user, orgId);

        List<Program> programs = org.get().getPrograms();
        return programs.stream().map(helper::buildProgramOrgMetaDTO).collect(Collectors.toList());
    }

    @Override
    public ProgramInvitationDTO createAndSendProgramInvitation(SessionUser user, Long programId,
                                                               ProgramInvitationDTO invitationDTO) throws ValidationException {
        Program program = getProgramRecord(programId);
        validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (!program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is not yet published");
        }
        AppUser appUser = null;
        if (invitationDTO.getUserId() != null) {
            appUser = appUserRepository.findById(invitationDTO.getUserId()).get();
        }
        ProgramInvitation programInvitation = helper.buildProgramInvitation(program, appUser, invitationDTO);
        programInvitationRepository.saveAndFlush(programInvitation);
        return invitationDTO;
    }

    @Override
    public List<ProgramInvitationDTO> getProgramInvitations(SessionUser user, Long programId) throws ValidationException {
        Program program = getProgramRecord(programId);
        validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (!program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is not yet published");
        }
        List<ProgramInvitation> programInvitations = programInvitationRepository.findByProgram(programId);
        if (CollectionUtils.isEmpty(programInvitations)) {
            return Collections.emptyList();
        }
        return helper.buildProgramInvitationDTOList(programInvitations);
    }

    private Program getProgramRecord(Long programId) {
        Optional<Program> dbRecordById = programRepository.findById(programId);
        if (! dbRecordById.isPresent()) {
            throw new ResourceNotFoundException("Program with Id not found in System");
        }
        return dbRecordById.get();
    }

    private void validateSessionUserOrgAccess(SessionUser user, Long orgId) throws ValidationException {
        OrganizationUser sessionOrgUser = getSessionOrgUser(user);
        if (! sessionOrgUser.getOrganization().getId().equals(orgId)) {
            throw new ValidationException("Program doesn't belong to current logged in users org");
        }
    }

    private OrganizationUser getSessionOrgUser(SessionUser user) {
        return orgUserRepository.findById(user.getId()).get();
    }

//    private String getLocalDateTime(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        return LocalDateTime.parse(date, formatter);
//    }
//
//    private String getDate(String dateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        return dateTime.format(formatter);
//    }
}
