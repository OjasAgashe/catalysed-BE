package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.ProgramApplication;
import com.ojas.gcp.firstappenginetryout.entity.ProgramParticipant;
import com.ojas.gcp.firstappenginetryout.entity.ProgramUserPK;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.AppUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProgramApplicationRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProgramParticipantRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProgramRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.applications.*;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgMentorDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgStudentDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.service.DirectoryService;
import com.ojas.gcp.firstappenginetryout.service.ProgramApplicationService;
import com.ojas.gcp.firstappenginetryout.service.helper.AuthValidationHelper;
import com.ojas.gcp.firstappenginetryout.service.helper.ProgramsHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.xml.bind.ValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramApplicationServiceImpl implements ProgramApplicationService {
    private final AuthValidationHelper authHelper;
    private final ProgramRepository programRepository;
    private final ProgramApplicationRepository applicationRepository;
    private final AppUserRepository appUserRepository;
    private final DirectoryService directoryService;
    private final ProgramParticipantRepository participantRepository;
    private final ProgramsHelper programsHelper;

    public ProgramApplicationServiceImpl(AuthValidationHelper authHelper, ProgramRepository programRepository,
                                         ProgramApplicationRepository applicationRepository, AppUserRepository appUserRepository,
                                         DirectoryService directoryService, ProgramParticipantRepository participantRepository,
                                         ProgramsHelper programsHelper) {
        this.authHelper = authHelper;
        this.programRepository = programRepository;
        this.applicationRepository = applicationRepository;
        this.appUserRepository = appUserRepository;
        this.directoryService = directoryService;
        this.participantRepository = participantRepository;
        this.programsHelper = programsHelper;
    }

    @Override
    public List<OrgProgramUserApplicationDTO> getProgramApplications(SessionUser user, Long programId, UserType applicantType) throws ValidationException {
        Program program = programRepository.findById(programId).get();
        authHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        List<ProgramApplication> programApplications = applicationRepository.findByProgramIdAndUserType(programId, applicantType);
        if (programApplications.isEmpty()) {
            return new ArrayList<>();
        }
        List<OrgProgramUserApplicationDTO> result = programApplications.stream()
                .map(application -> new OrgProgramUserApplicationDTO(
                        application.getId(),
                        application.getApplicant().getId(),
                        programId,
                        application.getApplicant().getFirstName() + " " + application.getApplicant().getFirstName(),
                        application.getApplicant().getEmail(),
                        applicantType,
                        application.getStatus(),
                        application.isReapplied(),
                        application.getAppliedOn(),
                        application.isViewedByOrg(),
                        new ArrayList<>()
                ))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ProgramStudentApplicationDetailsDTO getProgramStudentApplicationDetails(SessionUser user, Long applicationId) throws ValidationException {
        ProgramApplication programApplication = authorizeAndGetApplication(user, applicationId);
        OrgStudentDirectoryDTO studentDetails = directoryService.getConnectedStudent(programApplication.getOrgId(), programApplication.getApplicant().getId());

        ProgramStudentApplicationDetailsDTO studentApplication = new ProgramStudentApplicationDetailsDTO(
                studentDetails,
                buildApplicationDetails(programApplication)
        );

        //set application fetched by org if not set - this is a 2 step validation for setting the property isViewedByOrg
        if (!programApplication.isFetchedByOrg()) {
            programApplication.setFetchedByOrg(true);
            applicationRepository.saveAndFlush(programApplication);
        }
        return studentApplication;
    }

    @Override
    public ProgramMentorApplicationDetailsDTO getProgramMentorApplicationDetails(SessionUser user, Long applicationId) throws ValidationException {
        ProgramApplication programApplication = authorizeAndGetApplication(user, applicationId);
        OrgMentorDirectoryDTO mentorDetails = directoryService.getConnectedMentor(programApplication.getOrgId(), programApplication.getApplicant().getId());

        ProgramMentorApplicationDetailsDTO studentApplication = new ProgramMentorApplicationDetailsDTO(
                mentorDetails,
                buildApplicationDetails(programApplication)
        );

        if (!programApplication.isFetchedByOrg()) {
            programApplication.setFetchedByOrg(true);
            applicationRepository.saveAndFlush(programApplication);
        }
        return studentApplication;
    }


    @Override
    public OrgProgramUserApplicationDTO createProgramApplication(SessionUser user, Long programId, Long studentId,
                                                                 OrgProgramUserApplicationDTO applicationDTO) throws ValidationException {
        Program program = programRepository.findById(programId).get();
//        authHelper.validateSessionUserOrgAccess(user, program.getOrganization().getId());
        Optional<ProgramApplication> applicationRecordInDB = applicationRepository.findByProgramIdAndApplicantId(programId, studentId);
        if (applicationRecordInDB.isPresent()) {
            throw new ValidationException("User has already applied to the program");
        }
        AppUser student = appUserRepository.findById(studentId).get();
        ProgramApplication application = buildProgramApplication(applicationDTO, student, program);
        applicationRepository.saveAndFlush(application);
        applicationDTO.setId(application.getId());
        return applicationDTO;
    }

    @Override
    public void setIsViewedByOrgOnApplication(SessionUser user, Long applicationId) throws ValidationException {
        ProgramApplication application = authorizeAndGetApplication(user, applicationId);
        if (application.isFetchedByOrg() && !application.isViewedByOrg()) {
            application.setViewedByOrg(true);
            applicationRepository.saveAndFlush(application);
        }
    }

    @Override
    public void updateApplicationStatus(SessionUser user, Long applicationId, ProgramApplicationStatus statusChange) throws ValidationException {
        ProgramApplication application = authorizeAndGetApplication(user, applicationId);
        if (application.getStatus() == ProgramApplicationStatus.PENDING && statusChange != ProgramApplicationStatus.PENDING) {
            application.setStatus(statusChange);
            applicationRepository.saveAndFlush(application);
            //update the applicant as program participant.
            if (application.getStatus() == ProgramApplicationStatus.APPROVED) {
                ProgramParticipant participant = createProgramParticipantRecord(application);
                participantRepository.saveAndFlush(participant);
            }
        } else {
            throw new ValidationException("Invalid Status update request.");
        }
    }

    @Override
    public List<LiteProgramUserApplication> getApplicationsListForUser(SessionUser user, Long userId) throws ValidationException {
        //validate the user
        List<ProgramApplication> applications = applicationRepository.findByApplicantIdOrderByAppliedOnDesc(user.getId());
        return buildLiteApplications(applications);
    }

    @Override
    public UserProgramApplicationDTO getApplicantApplication(SessionUser user, Long userId, Long applicationId) throws ValidationException {
        //validate the user
        Optional<ProgramApplication> applicationRecord = applicationRepository.findById(applicationId);
        if (!applicationRecord.isPresent()) {
            throw new ValidationException("Program Application not Found");
        }
        ProgramApplication application = applicationRecord.get();
        if (application.getApplicant().getId() != user.getId()) {
            throw new ValidationException("Access Denied - user is not an applicant of this application");
        }
        return buildUserApplication(application, user.getUserType());
    }

    private ProgramParticipant createProgramParticipantRecord(ProgramApplication application) {
        ProgramParticipant participant = new ProgramParticipant();
        participant.setOrganization(application.getProgram().getOrganization());
        participant.setProgram(application.getProgram());
        participant.setUser(application.getApplicant());
        participant.setUserType(application.getUserType());
        ProgramUserPK pk = new ProgramUserPK(participant.getProgram().getId(), participant.getUser().getId(), participant.getOrganization().getId());

        participant.setId(pk);
        return participant;
    }

//    @Override
//    public ProgramUserApplicationDTO createMentorProgramApplication(SessionUser user, Long programId, Long mentorId,
//                                                                    ProgramUserApplicationDTO applicationDTO) {
//        return null;
//    }

    private ProgramApplication authorizeAndGetApplication(SessionUser user, Long applicationId) throws ValidationException {
        Optional<ProgramApplication> programApplicationsOptional = applicationRepository.findById(applicationId);
        if (!programApplicationsOptional.isPresent()) {
            throw new ValidationException(String.format("Application %d not found", applicationId));
        }
        ProgramApplication application = programApplicationsOptional.get();
        authHelper.validateSessionUserOrgAccess(user, application.getOrgId());

        return programApplicationsOptional.get();
    }

    private OrgProgramUserApplicationDTO buildApplicationDetails(ProgramApplication application) {
        List<ProgramApplicationResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(new ProgramApplicationResponseDTO(1, application.getQuestion1(), application.getAnswer1()));
        OrgProgramUserApplicationDTO applicationDetails = new OrgProgramUserApplicationDTO(
                application.getId(),
                application.getApplicant().getId(),
                application.getProgram().getId(),
                application.getApplicant().getFirstName() + application.getApplicant().getLastName(),
                application.getApplicant().getEmail(),
                application.getUserType(),
                application.getStatus(),
                application.isReapplied(),
                application.getAppliedOn(),
                application.isViewedByOrg(),
                responseDTOList
        );

        return applicationDetails;
    }

    private ProgramApplication buildProgramApplication(OrgProgramUserApplicationDTO applicationDTO, AppUser applicant, Program program) {
        ProgramApplication application = new ProgramApplication();
//        application.setId(applicationDTO.getId());
        application.setApplicant(applicant);
        application.setProgram(program);
        application.setUserType(applicationDTO.getApplicantType());
        application.setStatus(ProgramApplicationStatus.PENDING);
        application.setAppliedOn(OffsetDateTime.now(ZoneOffset.UTC).toString());
        application.setOrgId(program.getOrganization().getId());
        application.setReapplied(applicationDTO.isReapplied());
        application.setViewedByOrg(false);
        ProgramApplicationResponseDTO responseDTO = applicationDTO.getApplicationResponses().get(0);
        application.setQuestion1(responseDTO.getQuestion());
        application.setAnswer1(responseDTO.getAnswer());
        return application;
    }

    private List<LiteProgramUserApplication> buildLiteApplications(List<ProgramApplication> applications) {
        List<LiteProgramUserApplication> liteApplicationsList = new ArrayList<>();
        if (CollectionUtils.isEmpty(applications)) {
            return liteApplicationsList;
        }

        applications.stream().forEach(application -> {
            LiteProgramUserApplication liteApplication = new LiteProgramUserApplication(
                    application.getProgram().getId(),
                    application.getProgram().getTitle(),
                    application.getId(),
                    application.getStatus(),
                    application.getAppliedOn()
            );
            liteApplicationsList.add(liteApplication);
        });
        return liteApplicationsList;
    }

    private UserProgramApplicationDTO buildUserApplication(ProgramApplication application, UserType userType) {
        ProgramDTO programDetails = programsHelper.buildProgramDTO(application.getProgram(), userType);

        List<ProgramApplicationResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(new ProgramApplicationResponseDTO(1, application.getQuestion1(), application.getAnswer1()));
        UserProgramApplicationDTO applicationDTO = new UserProgramApplicationDTO(
                application.getId(),
                application.getStatus(),
                application.getAppliedOn(),
                responseDTOList,
                programDetails
        );
        return applicationDTO;
    }
}
