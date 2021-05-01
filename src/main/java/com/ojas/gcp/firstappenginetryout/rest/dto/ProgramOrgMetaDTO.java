package com.ojas.gcp.firstappenginetryout.rest.dto;

public class ProgramOrgMetaDTO {
    private Long id;
    private String title;
    private String tentativeStartDate;
    private int durationInMonths;
    private String mode;
    private String languageRequirements;

    public ProgramOrgMetaDTO() {
    }

    public ProgramOrgMetaDTO(Long id, String title, String tentativeStartDate, int durationInMonths,
                             String mode, String languageRequirements) {
        this.id = id;
        this.title = title;
        this.tentativeStartDate = tentativeStartDate;
        this.durationInMonths = durationInMonths;
        this.mode = mode;
        this.languageRequirements = languageRequirements;
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
}
