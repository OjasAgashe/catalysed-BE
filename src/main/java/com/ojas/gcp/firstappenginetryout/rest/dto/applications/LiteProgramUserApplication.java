package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;

public class LiteProgramUserApplication {
    private Long programId;
    private String programName;
    private Long applicationId;
    private ProgramApplicationStatus status;
    private String appliedOn;

    public LiteProgramUserApplication() {
    }

    public LiteProgramUserApplication(Long programId, String programName, Long applicationId,
                                      ProgramApplicationStatus status, String appliedOn) {
        this.programId = programId;
        this.programName = programName;
        this.applicationId = applicationId;
        this.status = status;
        this.appliedOn = appliedOn;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
}
