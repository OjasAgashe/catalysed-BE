package com.ojas.gcp.firstappenginetryout.rest.dto.applications;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

import java.util.List;

public class OrgProgramUserApplicationDTO {
    private Long id;
    private Long userId;
    private Long programId;
    private UserType applicantType;
    private String name;
    private String email;
    private ProgramApplicationStatus status;
    private boolean reapplied;
    private String appliedOn;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private boolean viewedByOrg;
    private List<ProgramApplicationResponseDTO> applicationResponses;

    public OrgProgramUserApplicationDTO() {

    }

    public OrgProgramUserApplicationDTO(Long id, Long userId, Long programId, String name, String email, UserType applicantType,
                                        ProgramApplicationStatus status, boolean reapplied, String appliedOn, boolean viewedByOrg,
                                        List<ProgramApplicationResponseDTO> applicationResponses) {
        this.id = id;
        this.userId = userId;
        this.programId = programId;
        this.name = name;
        this.email = email;
        this.applicantType = applicantType;
        this.status = status;
        this.reapplied = reapplied;
        this.appliedOn = appliedOn;
        this.viewedByOrg = viewedByOrg;
        this.applicationResponses = applicationResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(UserType applicantType) {
        this.applicantType = applicantType;
    }

    public ProgramApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramApplicationStatus status) {
        this.status = status;
    }

    public boolean isReapplied() {
        return reapplied;
    }

    public void setReapplied(boolean reapplied) {
        this.reapplied = reapplied;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public boolean isViewedByOrg() {
        return viewedByOrg;
    }

    public void setViewedByOrg(boolean viewedByOrg) {
        this.viewedByOrg = viewedByOrg;
    }

    public List<ProgramApplicationResponseDTO> getApplicationResponses() {
        return applicationResponses;
    }

    public void setApplicationResponses(List<ProgramApplicationResponseDTO> applicationResponses) {
        this.applicationResponses = applicationResponses;
    }
}
