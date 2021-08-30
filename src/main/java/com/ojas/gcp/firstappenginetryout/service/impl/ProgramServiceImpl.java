package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.*;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationType;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.exception.BadRequestBody;
import com.ojas.gcp.firstappenginetryout.exception.DuplicateResourceException;
import com.ojas.gcp.firstappenginetryout.exception.ResourceNotFoundException;
import com.ojas.gcp.firstappenginetryout.repository.*;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramLite;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramMeta;
import com.ojas.gcp.firstappenginetryout.repository.projection.ProgramMeta;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.UserViewProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.UserViewProgramInviteMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.StudentViewProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.registration.RegistrationInviteCheckDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import com.ojas.gcp.firstappenginetryout.service.helper.AuthValidationHelper;
import com.ojas.gcp.firstappenginetryout.service.helper.ProgramsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

import static com.ojas.gcp.firstappenginetryout.entity.enums.UserType.ORGANIZATION_USER;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final OrganizationRepository orgRepository;
    private final AppUserRepository appUserRepository;
    private final OrgUserConnectionRepository orgUserConnectionRepository;
    private final ProgramInvitationDetailsRepository programInvitationDetailsRepository;
    private final ProgramInvitationsRepository programInvitationRepository;
    private final AppInvitationsRepository appInvitationsRepository;
    private final ProgramParticipantRepository programParticipantRepository;
    private final ProgramApplicationRepository applicationRepository;
    private final ProgramsHelper helper;
    private final AuthValidationHelper authValidationHelper;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository, OrganizationRepository orgRepository,
                              AppUserRepository appUserRepository, ProgramInvitationDetailsRepository programInvitationDetailsRepository,
                              ProgramInvitationsRepository programInvitationRepository, OrgUserConnectionRepository orgUserConnectionRepository,
                              AppInvitationsRepository appInvitationsRepository, ProgramParticipantRepository programParticipantRepository,
                              ProgramsHelper helper, ProgramApplicationRepository applicationRepository, AuthValidationHelper authValidationHelper) {
        this.programRepository = programRepository;
        this.orgRepository = orgRepository;
        this.appUserRepository = appUserRepository;
        this.programInvitationDetailsRepository = programInvitationDetailsRepository;
        this.programInvitationRepository = programInvitationRepository;
        this.orgUserConnectionRepository = orgUserConnectionRepository;
        this.appInvitationsRepository = appInvitationsRepository;
        this.programParticipantRepository = programParticipantRepository;
        this.helper = helper;
        this.applicationRepository = applicationRepository;
        this.authValidationHelper = authValidationHelper;
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
        OrganizationUser sessionOrgUser = authValidationHelper.getSessionOrgUser(user);

        Optional<Program> dbRecordByTitle = programRepository.findByTitle(programDTO.getTitle());
        if (dbRecordByTitle.isPresent() && dbRecordByTitle.get().getOrganization().getId().equals(sessionOrgUser.getOrganization().getId())) {
            throw new DuplicateResourceException("Another Program exists with the same Title, please change the title");
        }
        Program program = helper.buildProgram(programDTO);
        program.setOrganization(sessionOrgUser.getOrganization());
        programRepository.saveAndFlush(program);
        return helper.buildProgramDTO(program, ORGANIZATION_USER);
    }


    @Override
    public void updateProgram(SessionUser user, ProgramDTO programDTO) throws Exception {
        if (programDTO.getId() == null) {
            throw new BadRequestBody("program Id not present in request");
        }

        Program program = getProgramRecord(programDTO.getId());
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        helper.setProgramDetails(programDTO, program);
        programRepository.saveAndFlush(program);
//        return buildProgramDTO(program);
    }

    @Override
    public void updateProgramStatus(SessionUser user, Long programId) throws Exception {
        Program program = getProgramRecord(programId);
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is already published");
        }
        program.setStatus(ProgramStatus.PUBLISHED);
        programRepository.saveAndFlush(program);
    }

    @Override
    public ProgramDTO getProgram(SessionUser user, Long id) throws Exception {
        Program program = getProgramRecord(id);
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        return helper.buildProgramDTO(program, ORGANIZATION_USER);
    }

    @Override
    public List<ProgramOrgMetaDTO> getProgramsForOrg(SessionUser user, Long orgId) throws ValidationException {
        //TO_DO :  findAllByOrgId
        Optional<Organization> org = orgRepository.findById(orgId);
        if(!org.isPresent()) {
            throw new ValidationException("Invalid org Id");
        }
        authValidationHelper.validateSessionUserOrgAccess(user, orgId);

        List<Program> programs = org.get().getPrograms();
        return programs.stream().map(helper::buildProgramOrgMetaDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProgramOrgMetaDTO> getProgramsMetaForOrgById(Long orgId) throws ValidationException {
        //TO_DO :  findAllByOrgId
        Optional<Organization> org = orgRepository.findById(orgId);
        if(!org.isPresent()) {
            throw new ValidationException("Invalid org Id");
        }
        List<Program> programs = org.get().getPrograms();
        return programs.stream().map(helper::buildProgramOrgMetaDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProgramOrgMetaDTO> getConnectedProgramsForUser(Long userId) throws ValidationException {
        List<ParticipantProgramMeta> connectedPrograms = programParticipantRepository.findByIdUserId(userId);
        return connectedPrograms.stream().map(helper::buildProgramOrgMetaDTO).collect(Collectors.toList());
    }

    @Override
    public ProgramDTO getConnectedProgramDetailsForUser(Long userId, Long programId, UserType userType) throws ValidationException {
        Optional<ProgramParticipant> connectedProgram = programParticipantRepository.findByIdUserIdAndIdProgramId(userId, programId);
        return  helper.buildProgramDTO(connectedProgram.get().getProgram(), userType);
    }

    @Override
    public List<ProgramOrgMetaDTO> getSuggestedProgramsForUser(Long userId) throws ValidationException {
        // get those programs that belong to the logged-in users connected org, But are not already part of the users connected program
        List<OrgUserConnection> orgUserConnection = orgUserConnectionRepository.findByIdUserId(userId);
        if(CollectionUtils.isEmpty(orgUserConnection)) {
            throw new ValidationException("User not yet connected with any Organization");
        }
        Long orgId = orgUserConnection.get(0).getOrganization().getId();
        List<Long> programsUserIsPartOf = programParticipantRepository.findByIdUserIdAndIdOrgId(userId, orgId)
                .stream().map(ParticipantProgramLite::getId).collect(Collectors.toList());
        programsUserIsPartOf.addAll(
                applicationRepository.findByApplicantId(userId)
                        .stream().map(application -> application.getApplicant().getId()).collect(Collectors.toList())
        );
        List<ProgramMeta> suggestedPrograms = programRepository.findByOrganizationIdAndIdNotInAndStatusNot(orgId, programsUserIsPartOf, ProgramStatus.SAVED_TO_DRAFT);
        return suggestedPrograms.stream().map(helper::buildProgramOrgMetaDTO).collect(Collectors.toList());
    }

    @Override
    public ProgramDTO getSuggestedProgramDetailsForUser(Long userId, Long programId, UserType userType) throws ValidationException {
        // validate that the program is not connected with the user
        Optional<ProgramParticipant> connectedProgram = programParticipantRepository.findByIdUserIdAndIdProgramId(userId, programId);
        if (connectedProgram.isPresent()) {
            throw new ValidationException("User is part of the program");
        }
        Program suggestedProgram = programRepository.findById(programId).get();
        return  helper.buildProgramDTO(suggestedProgram, userType);
    }

    @Override
    public ProgramInvitationDTO createAndSendProgramInvitation(SessionUser user, Long programId,
                                                               ProgramInvitationDTO invitationDTO) throws ValidationException {
        Program program = getProgramRecord(programId);
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (!program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is not yet published");
        }
        Optional<ProgramInvitation> invitationExists = programInvitationRepository.findByProgramIdAndEmailId(programId, invitationDTO.getEmailId());
        if (invitationExists.isPresent()) {
            throw new ValidationException(String.format("Program invitation for email : %s is already created", invitationDTO.getEmailId()));
        }

        AppUser appUser = null;
        Optional<AppUser> appUserRecord = appUserRepository.findByEmail(invitationDTO.getEmailId());
        if (appUserRecord.isPresent()) {
            appUser = appUserRecord.get();
        }
        ProgramInvitation programInvitation = helper.buildProgramInvitation(program, appUser, invitationDTO);
        programInvitationDetailsRepository.saveAndFlush(programInvitation.getInvitationDetails());
        programInvitationRepository.saveAndFlush(programInvitation);

        if (appUser == null) {
            AppInvitation appInvitation = new AppInvitation(
                    program.getOrganization(),
                    UUID.randomUUID().toString(),
                    invitationDTO.getEmailId(),
                    InvitationType.PROGRAM,
                    programId,
                    InvitationStatus.PENDING,
                    invitationDTO.getUserType(),
                    false
            );
            appInvitationsRepository.saveAndFlush(appInvitation);
        }
        //send email for program Invite
        return helper.buildProgramInvitationDTO(programInvitation);
    }

    @Override
    public List<ProgramInvitationDTO> getProgramInvitationsForOrgUser(SessionUser user, Long programId) throws ValidationException {
        Program program = getProgramRecord(programId);
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (!program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is not yet published");
        }
        List<ProgramInvitation> programInvitations = programInvitationRepository.findByProgramId(programId);
        if (CollectionUtils.isEmpty(programInvitations)) {
            return Collections.emptyList();
        }
        return helper.buildProgramInvitationDTOList(programInvitations);
    }

    @Override
    public List<UserViewProgramInviteMetaDTO> getProgramInvitationsForUser(SessionUser user) throws ValidationException {
        //get all invitations that are created for the users email Id
        List<ProgramInvitation> programInvitations = programInvitationRepository.findByEmailId(user.getEmailId());
        return programInvitations.stream().map(invitation -> helper.buildUserViewFOrProgramInvite(invitation)).collect(Collectors.toList());
    }

    @Override
    public UserViewProgramInvitationDTO getProgramInvitationDetailsForUser(SessionUser user, Long inviteId) throws ValidationException {
        //  Invitation | Program Details | participants
        //  program name -> program details
        // org details -> basic and then redirect to the org details page in directory
        Optional<ProgramInvitation> programInvitationRecord = programInvitationRepository.findById(inviteId);
        if (!programInvitationRecord.isPresent()) {
            throw new ValidationException("Invite not found");
        }
        ProgramInvitation programInvitation = programInvitationRecord.get();
        if (!programInvitation.getEmailId().equals(user.getEmailId())) {
            throw new ValidationException("Current user is not Invite recipient");
        }
        return new UserViewProgramInvitationDTO(
                helper.buildProgramInvitationDTO(programInvitation),
                helper.buildProgramDTO(programInvitation.getProgram(), user.getUserType())
        );
    }

    @Override
    public void updateProgramInviteResponseByUser(SessionUser user, Long inviteId, InvitationStatus responseType) throws ValidationException {
        Optional<ProgramInvitation> programInvitationRecord = programInvitationRepository.findById(inviteId);
        if (!programInvitationRecord.isPresent()) {
            throw new ValidationException("Invite not found");
        }
        ProgramInvitation programInvitation = programInvitationRecord.get();
        if (!programInvitation.getEmailId().equals(user.getEmailId())) {
            throw new ValidationException("Current user is not Invite recipient");
        }
        if (programInvitation.getResponseStatus() == InvitationStatus.PENDING &&
                (responseType == InvitationStatus.ACCEPTED || responseType == InvitationStatus.REJECTED)) {
            programInvitation.setResponseStatus(responseType);
            programInvitationRepository.saveAndFlush(programInvitation);
            if (responseType == InvitationStatus.ACCEPTED) {
                createAndSaveProgramParticipantRecord(programInvitation.getProgram().getId(), programInvitation.getRecipient());
            }
        } else {
            throw new ValidationException("Bad Invitation response update request");
        }
    }

    @Override
    public ProgramParticipantsDTO getProgramParticipants(SessionUser user, Long programId, UserType participantType) throws ValidationException {
        Program program = getProgramRecord(programId);
        authValidationHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        if (!program.getStatus().equals(ProgramStatus.PUBLISHED)) {
            throw new ValidationException("Program is not yet published");
        }
        List<ProgramParticipant> programParticipants = programParticipantRepository.findByIdProgramId(programId);
        return helper.buildProgramParticipantsDTO(programId, programParticipants);
    }

    @Override
    public StudentViewProgramParticipantsDTO getProgramParticipantsForStudents(Long userId, Long programId, UserType userType) throws ValidationException {
        //validate the user is connected to the program
        Optional<ProgramParticipant> connectedProgram = programParticipantRepository.findByIdUserIdAndIdProgramId(userId, programId);
        if (!connectedProgram.isPresent()) {
            throw new ValidationException("User not part of the program");
        }
        List<ProgramParticipant> programParticipants = programParticipantRepository.findByIdProgramId(programId);
        return helper.buildUserViewProgramParticipantsDTO(programId, programParticipants);
    }

    @Override
    public ProgramParticipantsDTO getProgramParticipantsForMentors(Long userId, Long programId, UserType userType) throws ValidationException {
        Optional<ProgramParticipant> connectedProgram = programParticipantRepository.findByIdUserIdAndIdProgramId(userId, programId);
        if (!connectedProgram.isPresent()) {
            throw new ValidationException("User not part of the program");
        }
        List<ProgramParticipant> programParticipants = programParticipantRepository.findByIdProgramId(programId);
        return helper.buildProgramParticipantsDTO(programId, programParticipants);
    }

    @Override
    public RegistrationInviteCheckDTO isValidApplicationProgramInvite(UUID inviteKey) throws ValidationException {
        Optional<AppInvitation> appInvitationDbRecord = appInvitationsRepository.findByInvitationKey(inviteKey.toString());
        if (!appInvitationDbRecord.isPresent()) {
            throw new ValidationException("Invalid Invite");
        }
        AppInvitation appInvitation = appInvitationDbRecord.get();
        if (appInvitation.isAccountCreated()) {
            throw new ValidationException("Account already created, please Login");
        }
        return new RegistrationInviteCheckDTO(
                true,
                appInvitation.getOrganization().getName(),
                appInvitation.getEmail(),
                appInvitation.getUserType()
        );
    }

    @Override
    public String tempGetInviteKey(String emailId) throws ValidationException {
        Optional<AppInvitation> appInvitationDbRecord = appInvitationsRepository.findByEmail(emailId);
        if (!appInvitationDbRecord.isPresent()) {
            throw new ValidationException(String.format("App invite not present for email  : %s", emailId));
        }
        return appInvitationDbRecord.get().getInvitationKey();
    }

    @Override
    public void updateApplicationProgramInviteAndAddProgramParticipant(String userEmail, AppUser appUser){
        Optional<AppInvitation> appInvitationDbRecord = appInvitationsRepository.findByEmail(userEmail);
        if (appInvitationDbRecord.isPresent() && appInvitationDbRecord.get().getType() == InvitationType.PROGRAM) {
            AppInvitation appInvitation = appInvitationDbRecord.get();
            appInvitation.setStatus(InvitationStatus.ACCEPTED);
            appInvitationsRepository.saveAndFlush(appInvitation);

            Optional<ProgramInvitation> programInvitationRecord = programInvitationRepository.findByProgramIdAndEmailId(appInvitation.getProgramId(), userEmail);
            if (programInvitationRecord.isPresent()) {
                ProgramInvitation programInvitation = programInvitationRecord.get();
                programInvitation.setResponseStatus(InvitationStatus.ACCEPTED);
                programInvitation.setRecipient(appUser);
                programInvitationRepository.saveAndFlush(programInvitation);
            }

            createAndSaveProgramParticipantRecord(appInvitation.getProgramId(), appUser);
        }
    }

    private void createAndSaveProgramParticipantRecord(Long programId, AppUser appUser) {
        Program program = getProgramRecord(programId);
        ProgramParticipant programParticipant = new ProgramParticipant();
        programParticipant.setProgram(program);
        programParticipant.setOrganization(program.getOrganization());
        programParticipant.setUser(appUser);
        programParticipant.setUserType(programParticipant.getUser().getType());
        ProgramUserPK pk = new ProgramUserPK(programParticipant.getProgram().getId(), programParticipant.getUser().getId(),
                programParticipant.getOrganization().getId());

        programParticipant.setId(pk);
        programParticipantRepository.saveAndFlush(programParticipant);
    }

    private Program getProgramRecord(Long programId) {
        Optional<Program> dbRecordById = programRepository.findById(programId);
        if (! dbRecordById.isPresent()) {
            throw new ResourceNotFoundException("Program with Id not found in System");
        }
        return dbRecordById.get();
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
