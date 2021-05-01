package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.ProgramMentorFields;
import com.ojas.gcp.firstappenginetryout.entity.ProgramStudentFields;
import com.ojas.gcp.firstappenginetryout.repository.ProgramRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramOrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public ProgramDTO createProgram(ProgramDTO programDTO) throws Exception {
        //push to separate validation methods
        if (programDTO.getId() != null) {
            Optional<Program> dbRecordById = programRepository.findById(programDTO.getId());
            if (dbRecordById.isPresent()) {
                throw new Exception("Program with Id already present");
            }
        }
        Optional<Program> dbRecordByTitle = programRepository.findByTitle(programDTO.getTitle());
        if (dbRecordByTitle.isPresent()) {
            throw new Exception("Another Program exists with the same Title, please change the title");
        }
        Program program = buildProgram(programDTO);
        programRepository.saveAndFlush(program);
        return buildProgramDTO(program);
    }

    @Override
    public ProgramDTO getProgram(Long id) throws Exception {
        Optional<Program> dbRecordById = programRepository.findById(id);
        if (!dbRecordById.isPresent()) {
            throw new Exception("Program with Id does not exist");
        }
        return buildProgramDTO(dbRecordById.get());
    }

    @Override
    public List<ProgramOrgMetaDTO> getProgramsForOrg(Long orgId) {
        //TO_DO :  findAllByOrgId
        List<Program> programs = programRepository.findAll();
        return programs.stream().map(program -> buildProgramOrgMetaDTO(program)).collect(Collectors.toList());
    }


    private Program buildProgram(ProgramDTO programDTO) {
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
        program.setCoordinatorPhoneExtension(programDTO.getCoordinator().getPhoneExtension());
        program.setCoordinatorPhoneNumber(programDTO.getCoordinator().getPhoneNumber());

        ProgramDTO.StudentFields studentFieldsDTO = programDTO.getStudentFields();
        ProgramStudentFields studentFields = new ProgramStudentFields(
                studentFieldsDTO.getSubjectRequirements(),
                studentFieldsDTO.getOpenings(),
                studentFieldsDTO.getApplyBy(),
                studentFieldsDTO.isPaid(),
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

    private ProgramDTO buildProgramDTO(Program program) {
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
                new ProgramDTO.Coordinator(program.getCoordinatorName(), program.getCoordinatorEmail(), program.getCoordinatorPhoneExtension(), program.getCoordinatorPhoneNumber()),
                new ProgramDTO.StudentFields(studentFields.getSubjectRequirements(), studentFields.getOpenings(), studentFields.isPaid(), studentFields.getApplyBy(), studentFields.getProgramFees(), studentFields.getGeneralInstructions()),
                new ProgramDTO.MentorFields(mentorFields.getSubjectRequirements(), mentorFields.getOpenings(), mentorFields.getApplyBy(), mentorFields.getGeneralInstructions())
        );
        return programDTO;
    }

    private ProgramOrgMetaDTO buildProgramOrgMetaDTO(Program program) {
        return new ProgramOrgMetaDTO(
                program.getId(),
                program.getTitle(),
                program.getTentativeStartDate(),
                program.getDurationInMonths(),
                program.getMode(),
                program.getLanguageRequirements()
        );
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
