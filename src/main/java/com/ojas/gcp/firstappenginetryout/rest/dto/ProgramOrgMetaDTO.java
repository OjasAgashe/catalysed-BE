package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;

public class ProgramOrgMetaDTO {
    private Long id;
    private String title;
    private String tentativeStartDate;
    private int durationInMonths;
    private String mode;
    private String languageRequirements;
    private ProgramStatus status;

    public ProgramOrgMetaDTO() {
    }

    public ProgramOrgMetaDTO(Long id, String title, String tentativeStartDate, int durationInMonths,
                             String mode, String languageRequirements, ProgramStatus status) {
        this.id = id;
        this.title = title;
        this.tentativeStartDate = tentativeStartDate;
        this.durationInMonths = durationInMonths;
        this.mode = mode;
        this.languageRequirements = languageRequirements;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTentativeStartDate() {
        return tentativeStartDate;
    }

    public void setTentativeStartDate(String tentativeStartDate) {
        this.tentativeStartDate = tentativeStartDate;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLanguageRequirements() {
        return languageRequirements;
    }

    public void setLanguageRequirements(String languageRequirements) {
        this.languageRequirements = languageRequirements;
    }

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramStatus status) {
        this.status = status;
    }
}
