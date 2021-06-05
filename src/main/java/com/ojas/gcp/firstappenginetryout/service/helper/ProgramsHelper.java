package com.ojas.gcp.firstappenginetryout.service.helper;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.ProgramInvitation;
import com.ojas.gcp.firstappenginetryout.entity.ProgramInvitationDetails;
import com.ojas.gcp.firstappenginetryout.entity.ProgramMentorFields;
import com.ojas.gcp.firstappenginetryout.entity.ProgramStudentFields;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramInvitationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramsHelper {

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

    public ProgramDTO buildProgramDTO(Program program) {
        ProgramStudentFields studentFields = program.getStudentFields();
        ProgramMentorFields mentorFields = program.getMentorFields();
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
                new ProgramDTO.StudentFields(studentFields.getSubjectRequirements(), studentFields.getOpenings(), studentFields.isPaid(), studentFields.getApplyBy(), studentFields.getProgramFees(), studentFields.getGeneralInstructions()),
                new ProgramDTO.MentorFields(mentorFields.getSubjectRequirements(), mentorFields.getOpenings(), mentorFields.getApplyBy(), mentorFields.getGeneralInstructions()),
                program.getStatus()
        );
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

    public ProgramInvitation buildProgramInvitation(Program program, AppUser appUser, ProgramInvitationDTO invitationDTO) {
        ProgramInvitation programInvitation = new ProgramInvitation();
        programInvitation.setProgram(program);
        programInvitation.setRecipient(appUser);
        programInvitation.setEmailId(invitationDTO.getEmailId());
        programInvitation.setResponseStatus(InvitationStatus.NOT_RESPONDED);
        programInvitation.setSentAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
        programInvitation.setUserType(invitationDTO.getUserType());

        ProgramInvitationDetails details = new ProgramInvitationDetails();
        details.setMessage(invitationDTO.getMessage());
        details.setSubject(invitationDTO.getSubject());

        programInvitation.setInvitationDetails(details);
        return programInvitation;
    }

    public List<ProgramInvitationDTO> buildProgramInvitationDTOList(List<ProgramInvitation> programInvitationList) {
        List<ProgramInvitationDTO> invitationDTOList = new ArrayList<>();
        programInvitationList.forEach(invitation -> {
            ProgramInvitationDetails details = invitation.getInvitationDetails();
            invitationDTOList.add(new ProgramInvitationDTO(
                    invitation.getId(),
                    invitation.getProgram().getId(),
                    invitation.getRecipient().getId(),
                    invitation.getEmailId(),
                    invitation.getName(),
                    invitation.getUserType(),
                    invitation.getResponseStatus(),
                    details.getSubject(),
                    details.getMessage()
                    ));
        });
        return invitationDTOList;
    }
}
