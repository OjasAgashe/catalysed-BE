package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.rest.dto.ProgramDTO;
import java.util.List;

public class UserProgramApplicationDTO {
    private Long applicationId;
    private ProgramApplicationStatus status;
    private String appliedOn;
    private List<ProgramApplicationResponseDTO> applicationResponses;
    private ProgramDTO programDetails;

    public UserProgramApplicationDTO() {
    }

    public UserProgramApplicationDTO(Long applicationId, ProgramApplicationStatus status, String appliedOn,
                                     List<ProgramApplicationResponseDTO> applicationResponses, ProgramDTO programDetails) {
        this.applicationId = applicationId;
        this.status = status;
        this.appliedOn = appliedOn;
        this.applicationResponses = applicationResponses;
        this.programDetails = programDetails;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public ProgramApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramApplicationStatus status) {
        this.status = status;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public List<ProgramApplicationResponseDTO> getApplicationResponses() {
        return applicationResponses;
    }

    public void setApplicationResponses(List<ProgramApplicationResponseDTO> applicationResponses) {
        this.applicationResponses = applicationResponses;
    }

    public ProgramDTO getProgramDetails() {
        return programDetails;
    }

    public void setProgramDetails(ProgramDTO programDetails) {
        this.programDetails = programDetails;
    }
}
