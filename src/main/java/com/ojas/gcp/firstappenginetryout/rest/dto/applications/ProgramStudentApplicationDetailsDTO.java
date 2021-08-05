package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgStudentDirectoryDTO;

public class ProgramStudentApplicationDetailsDTO {
    private OrgStudentDirectoryDTO studentDetails;
    private OrgProgramUserApplicationDTO applicationDetails;

    public ProgramStudentApplicationDetailsDTO() {

    }

    public ProgramStudentApplicationDetailsDTO(OrgStudentDirectoryDTO studentDetails, OrgProgramUserApplicationDTO applicationDetails) {
        this.studentDetails = studentDetails;
        this.applicationDetails = applicationDetails;
    }

    public OrgStudentDirectoryDTO getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(OrgStudentDirectoryDTO studentDetails) {
        this.studentDetails = studentDetails;
    }

    public OrgProgramUserApplicationDTO getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(OrgProgramUserApplicationDTO applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
