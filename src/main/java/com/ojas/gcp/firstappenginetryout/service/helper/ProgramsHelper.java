package com.ojas.gcp.firstappenginetryout.service.helper;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.ProfileUserEO;
import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.ProgramInvitation;
import com.ojas.gcp.firstappenginetryout.entity.ProgramInvitationDetails;
import com.ojas.gcp.firstappenginetryout.entity.ProgramMentorFields;
import com.ojas.gcp.firstappenginetryout.entity.ProgramParticipant;
import com.ojas.gcp.firstappenginetryout.entity.ProgramStudentFields;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.ProfileUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramMeta;
import com.ojas.gcp.firstappenginetryout.repository.projection.ProgramMeta;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.invitations.UserViewProgramInviteMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantBaseDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.ProgramParticipantsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.participants.StudentViewProgramParticipantsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.xml.bind.ValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramsHelper {

    private ProfileUserRepository profileUserRepository;

    @Autowired
    public ProgramsHelper(ProfileUserRepository profileUserRepository) {
        this.profileUserRepository = profileUserRepository;
    }

    public void setProgramDetails(ProgramDTO programDTO, Program program) throws ValidationException {
        if (program.getStatus().equals(ProgramStatus.PUBLISHED) && programDTO.getStatus().equals(ProgramStatus.SAVED_TO_DRAFT)) {
            throw new ValidationException("Incorrect status update for program");
        }
        ProgramDTO.StudentFields studentFieldsDTO = programDTO.getStudentFields();
        ProgramStudentFields studentFields = program.getStudentFields();

        ProgramDTO.MentorFields mentorFieldsDTO = programDTO.getMentorFields();
        ProgramMentorFields mentorFields = program.getMentorFields();

        if(program.getStatus().equals(ProgramStatus.SAVED_TO_DRAFT)) {
            program.setTitle(programDTO.getTitle());
            program.setTentativeStartDate(programDTO.getTentativeStartDate());
            program.setDurationInMonths(programDTO.getDurationInMonths());
            program.setMode(programDTO.getMode());
            program.setLanguageRequirements(programDTO.getLanguageRequirements());
            program.setStudentAgeLimitFrom(programDTO.getAgeLimit().getFrom());
            program.setStudentAgeLimitTo(programDTO.getAgeLimit().getTo());

            studentFields.setSubjectRequirements(studentFieldsDTO.getSubjectRequirements());
            studentFields.setOpenings(studentFieldsDTO.getOpenings());
            studentFields.setProgramFees(studentFieldsDTO.getProgramFees());
            studentFields.setPaid(studentFieldsDTO.getPaid());
            studentFields.setApplyBy(studentFieldsDTO.getApplyBy());

            mentorFields.setApplyBy(mentorFieldsDTO.getApplyBy());
            mentorFields.setOpenings(mentorFieldsDTO.getOpenings());
            mentorFields.setSubjectRequirements(mentorFieldsDTO.getSubjectRequirements());
        }

        studentFields.setGeneralInstructions(studentFieldsDTO.getGeneralInstructions());
        mentorFields.setGeneralInstructions(mentorFieldsDTO.getGeneralInstructions());

        program.setStudentFields(studentFields);
        program.setMentorFields(mentorFields);

        program.setDescription(programDTO.getDescription());
        program.setProgramLink(programDTO.getProgramLink());
        program.setCoordinatorName(programDTO.getCoordinator().getName());
        program.setCoordinatorEmail(programDTO.getCoordinator().getEmail());
        program.setCoordinatorPhoneCountryName(programDTO.getCoordinator().getContact().getCountryName());
        program.setCoordinatorPhoneCountryCode(programDTO.getCoordinator().getContact().getCountryCode());
        program.setCoordinatorPhoneNumber(programDTO.getCoordinator().getContact().getNumber());
        program.setStatus(programDTO.getStatus());
    }

    public Program buildProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setTitle(programDTO.getTitle());
        program.setDescription(programDTO.getDescription());
        program.setTentativeStartDate(programDTO.getTentativeStartDate());
        program.setDurationInMonths(programDTO.getDurationInMonths());
        program.setMode(programDTO.getMode());
        program.setLanguageRequirements(programDTO.getLanguageRequirements());
        program.setStudentAgeLimitFrom(programDTO.getAgeLimit().getFrom());
        program.setStudentAgeLimitTo(programDTO.getAgeLimit().getTo());
        program.setProgramLink(programDTO.getProgramLink());
        program.setCoordinatorName(programDTO.getCoordinator().getName());
        program.setCoordinatorEmail(programDTO.getCoordinator().getEmail());
        program.setCoordinatorPhoneCountryName(programDTO.getCoordinator().getContact().getCountryName());
        program.setCoordinatorPhoneCountryCode(programDTO.getCoordinator().getContact().getCountryCode());
        program.setCoordinatorPhoneNumber(programDTO.getCoordinator().getContact().getNumber());
        program.setStatus(programDTO.getStatus());

        ProgramDTO.StudentFields studentFieldsDTO = programDTO.getStudentFields();
        ProgramStudentFields studentFields = new ProgramStudentFields(
                studentFieldsDTO.getSubjectRequirements(),
                studentFieldsDTO.getOpenings(),
                studentFieldsDTO.getApplyBy(),
                studentFieldsDTO.getPaid(),
                studentFieldsDTO.getProgramFees(),
                studentFieldsDTO.getGeneralInstructions(),
                program);

        ProgramDTO.MentorFields mentorFieldsDTO = programDTO.getMentorFields();
        ProgramMentorFields mentorFields = new ProgramMentorFields(
                mentorFieldsDTO.getSubjectRequirements(),
                mentorFieldsDTO.getOpenings(),
                mentorFieldsDTO.getApplyBy(),
                mentorFieldsDTO.getGeneralInstructions(),
                program);

        program.setStudentFields(studentFields);
        program.setMentorFields(mentorFields);

        return program;
    }

    public ProgramDTO buildProgramDTO(Program program, UserType userType) {
        ProgramDTO programDTO = new ProgramDTO(
                program.getId(),
                program.getTitle(),
                program.getDescription(),
                program.getTentativeStartDate(),
                program.getDurationInMonths(),
                program.getMode(),
                program.getLanguageRequirements(),
                new ProgramDTO.AgeLimit(program.getStudentAgeLimitFrom(), program.getStudentAgeLimitTo()),
                program.getProgramLink(),
                new ProgramDTO.Coordinator(program.getCoordinatorName(), program.getCoordinatorEmail(), new PhoneDTO(program.getCoordinatorPhoneCountryName(), program.getCoordinatorPhoneCountryCode(), program.getCoordinatorPhoneNumber())),
                null,
                null,
                program.getStatus()
        );

        ProgramStudentFields studentFields = null;
        ProgramMentorFields mentorFields = null;
        if (userType == UserType.ORGANIZATION_USER || userType == UserType.STUDENT) {
            studentFields = program.getStudentFields();
            programDTO.setStudentFields(new ProgramDTO.StudentFields(
                    studentFields.getSubjectRequirements(),
                    studentFields.getOpenings(),
                    studentFields.isPaid(),
                    studentFields.getApplyBy(),
                    studentFields.getProgramFees(),
                    studentFields.getGeneralInstructions()
                    )
            );
        }
        if (userType == UserType.ORGANIZATION_USER || userType == UserType.MENTOR) {
            mentorFields = program.getMentorFields();
            programDTO.setMentorFields(new ProgramDTO.MentorFields(
                    mentorFields.getSubjectRequirements(),
                    mentorFields.getOpenings(),
                    mentorFields.getApplyBy(),
                    mentorFields.getGeneralInstructions()
                    )
            );
        }
        return programDTO;
    }

    public ProgramOrgMetaDTO buildProgramOrgMetaDTO(Program program) {
        return new ProgramOrgMetaDTO(
                program.getId(),
                program.getTitle(),
                program.getTentativeStartDate(),
                program.getDurationInMonths(),
                program.getMode(),
                program.getLanguageRequirements(),
                program.getStatus()
        );
    }

    public ProgramOrgMetaDTO buildProgramOrgMetaDTO(ProgramMeta program) {
        return new ProgramOrgMetaDTO(
                program.getId(),
                program.getTitle(),
                program.getTentativeStartDate(),
                program.getDurationInMonths(),
                program.getMode(),
                program.getLanguageRequirements(),
                program.getStatus()
        );
    }

    public ProgramOrgMetaDTO buildProgramOrgMetaDTO(ParticipantProgramMeta programMeta) {
        return new ProgramOrgMetaDTO(
                programMeta.getId(),
                programMeta.getTitle(),
                programMeta.getTentativeStartDate(),
                programMeta.getDurationInMonths(),
                programMeta.getMode(),
                programMeta.getLanguageRequirements(),
                programMeta.getStatus()
        );
    }

    public ProgramInvitation buildProgramInvitation(Program program, AppUser appUser, ProgramInvitationDTO invitationDTO) {
        ProgramInvitation programInvitation = new ProgramInvitation();
        programInvitation.setProgram(program);
        programInvitation.setRecipient(appUser);
        programInvitation.setEmailId(invitationDTO.getEmailId());
        programInvitation.setResponseStatus(InvitationStatus.PENDING);
        programInvitation.setSentAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
        programInvitation.setUserType(invitationDTO.getUserType());
        programInvitation.setName(invitationDTO.getName());

        ProgramInvitationDetails details = new ProgramInvitationDetails();
        details.setMessage(invitationDTO.getMessage());
        details.setSubject(invitationDTO.getSubject());
        List<ProgramInvitation> invitations = new ArrayList<>();
        invitations.add(programInvitation);
        details.setInvitations(invitations);

        programInvitation.setInvitationDetails(details);
        return programInvitation;
    }

    public List<ProgramInvitationDTO> buildProgramInvitationDTOList(List<ProgramInvitation> programInvitationList) {
        List<ProgramInvitationDTO> invitationDTOList = new ArrayList<>();
        programInvitationList.forEach(invitation -> {
            invitationDTOList.add(buildProgramInvitationDTO(invitation));
        });
        return invitationDTOList;
    }

    public ProgramInvitationDTO buildProgramInvitationDTO(ProgramInvitation invitation) {
        ProgramInvitationDetails details = invitation.getInvitationDetails();
        return new ProgramInvitationDTO(
                invitation.getId(),
                invitation.getProgram().getId(),
                invitation.getRecipient() == null ? null : invitation.getRecipient().getId(),
                invitation.getEmailId(),
                invitation.getName(),
                invitation.getUserType(),
                invitation.getResponseStatus(),
                details.getSubject(),
                details.getMessage(),
                invitation.getSentAt()
        );
    }

    public ProgramParticipantsDTO buildProgramParticipantsDTO(Long programId, List<ProgramParticipant> programParticipants) {
        List<ProgramParticipantBaseDTO> studentParticipantList = new ArrayList<>();
        List<ProgramParticipantBaseDTO> mentorParticipantList = new ArrayList<>();
        programParticipants.forEach(programParticipant ->  {
            AppUser user = programParticipant.getUser();
            ProfileUserEO profile = profileUserRepository.findById(user.getId()).get();
            ProgramParticipantBaseDTO participantBaseDTO = new ProgramParticipantBaseDTO(profile.getId(), user.getFirstName(), user.getLastName(),
                    user.getEmail(), new PhoneDTO(profile.getContactPhoneCountryName(), profile.getContactPhoneCountryCode(), profile.getContactPhoneNumber()),
                    new LocationDTO(profile.getLocationCountry(), profile.getLocationRegion()));
            if (user.getType() == UserType.STUDENT) {
                studentParticipantList.add(participantBaseDTO);
            } else {
                mentorParticipantList.add(participantBaseDTO);
            }
        });
        
        ProgramParticipantsDTO programParticipantsDTO = new ProgramParticipantsDTO();
        programParticipantsDTO.setProgramId(programId);
        programParticipantsDTO.setStudentParticipants(studentParticipantList);
        programParticipantsDTO.setMentorParticipants(mentorParticipantList);
        return programParticipantsDTO;
    }

    public StudentViewProgramParticipantsDTO buildUserViewProgramParticipantsDTO(Long programId, List<ProgramParticipant> programParticipants) {
        List<ProgramParticipantBaseDTO> mentorParticipantList = new ArrayList<>();
        List<String> studentParticipantList = new ArrayList<>();
        programParticipants.forEach(programParticipant ->  {
            AppUser user = programParticipant.getUser();
            if (user.getType() == UserType.STUDENT) {
                studentParticipantList.add(user.getFirstName() + " " + user.getLastName());
            } else {
                ProfileUserEO profile = profileUserRepository.findById(user.getId()).get();
                ProgramParticipantBaseDTO participantBaseDTO = new ProgramParticipantBaseDTO(profile.getId(), user.getFirstName(), user.getLastName(),
                        user.getEmail(), new PhoneDTO(profile.getContactPhoneCountryName(), profile.getContactPhoneCountryCode(), profile.getContactPhoneNumber()),
                        new LocationDTO(profile.getLocationCountry(), profile.getLocationRegion()));
                mentorParticipantList.add(participantBaseDTO);
            }
        });

        StudentViewProgramParticipantsDTO programParticipantsDTO = new StudentViewProgramParticipantsDTO();
        programParticipantsDTO.setProgramId(programId);
        programParticipantsDTO.setStudents(studentParticipantList);
        programParticipantsDTO.setMentors(mentorParticipantList);
        return programParticipantsDTO;
    }

    public UserViewProgramInviteMetaDTO buildUserViewFOrProgramInvite(ProgramInvitation programInvitation) {
        Program program = programInvitation.getProgram();
        return new UserViewProgramInviteMetaDTO(
                program.getTitle(),
                program.getOrganization().getName(),
                programInvitation.getSentAt(),
                program.getTentativeStartDate(),
                programInvitation.getResponseStatus()
        );
    }

}
