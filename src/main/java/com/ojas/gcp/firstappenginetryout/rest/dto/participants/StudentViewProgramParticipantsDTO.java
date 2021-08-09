package com.ojas.gcp.firstappenginetryout.rest.dto.participants;

import java.util.ArrayList;
import java.util.List;

public class StudentViewProgramParticipantsDTO {
    private Long programId;
    private List<ProgramParticipantBaseDTO> mentors = new ArrayList<>();
    private List<String> students;

    public StudentViewProgramParticipantsDTO() {

    }

    public StudentViewProgramParticipantsDTO(Long programId, List<ProgramParticipantBaseDTO> mentors, List<String> students) {
        this.programId = programId;
        this.mentors = mentors;
        this.students = students;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public List<ProgramParticipantBaseDTO> getMentors() {
        return mentors;
    }

    public void setMentors(List<ProgramParticipantBaseDTO> mentors) {
        this.mentors = mentors;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
