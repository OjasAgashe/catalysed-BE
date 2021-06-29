package com.ojas.gcp.firstappenginetryout.rest.dto.participants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.UserDTO;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramParticipantsDTO {
    private Long programId;
    private List<ProgramParticipantBaseDTO> studentParticipants = new ArrayList<>();
    private List<ProgramParticipantBaseDTO> mentorParticipants = new ArrayList<>();

    public ProgramParticipantsDTO() {
    }

    public ProgramParticipantsDTO(Long programId, List<ProgramParticipantBaseDTO> studentParticipants, List<ProgramParticipantBaseDTO> mentorParticipants) {
        this.programId = programId;
        this.studentParticipants = studentParticipants;
        this.mentorParticipants = mentorParticipants;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public List<ProgramParticipantBaseDTO> getStudentParticipants() {
        return studentParticipants;
    }

    public void setStudentParticipants(List<ProgramParticipantBaseDTO> studentParticipants) {
        this.studentParticipants = studentParticipants;
    }

    public List<ProgramParticipantBaseDTO> getMentorParticipants() {
        return mentorParticipants;
    }

    public void setMentorParticipants(List<ProgramParticipantBaseDTO> mentorParticipants) {
        this.mentorParticipants = mentorParticipants;
    }
}
