package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgMentorDirectoryDTO;

public class ProgramMentorApplicationDetailsDTO {
    private OrgMentorDirectoryDTO mentorDetails;
    private OrgProgramUserApplicationDTO applicationDetails;

    public ProgramMentorApplicationDetailsDTO() {

    }

    public ProgramMentorApplicationDetailsDTO(OrgMentorDirectoryDTO studentDetails, OrgProgramUserApplicationDTO applicationDetails) {
        this.mentorDetails = studentDetails;
        this.applicationDetails = applicationDetails;
    }

    public OrgMentorDirectoryDTO getMentorDetails() {
        return mentorDetails;
    }

    public void setMentorDetails(OrgMentorDirectoryDTO mentorDetails) {
        this.mentorDetails = mentorDetails;
    }

    public OrgProgramUserApplicationDTO getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(OrgProgramUserApplicationDTO applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
