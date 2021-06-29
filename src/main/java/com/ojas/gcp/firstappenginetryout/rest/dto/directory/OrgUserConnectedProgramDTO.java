package com.ojas.gcp.firstappenginetryout.rest.dto.directory;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;

public class OrgUserConnectedProgramDTO {
    private Long programId;
    private ProgramStatus status;
    private String title;

    public OrgUserConnectedProgramDTO() {

    }

    public OrgUserConnectedProgramDTO(Long programId, ProgramStatus status, String title) {
        this.programId = programId;
        this.status = status;
        this.title = title;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
