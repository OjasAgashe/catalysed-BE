package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgApplicationsSummaryDTO {
    private String programName;
    private OrgApplicationsSummaryMetaDTO mentorSummary;
    private OrgApplicationsSummaryMetaDTO studentSummary;

    public OrgApplicationsSummaryDTO() {

    }

    public OrgApplicationsSummaryDTO(String programName, OrgApplicationsSummaryMetaDTO mentorSummary,
                                     OrgApplicationsSummaryMetaDTO studentSummary) {
        this.programName = programName;
        this.mentorSummary = mentorSummary;
        this.studentSummary = studentSummary;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public OrgApplicationsSummaryMetaDTO getMentorSummary() {
        return mentorSummary;
    }

    public void setMentorSummary(OrgApplicationsSummaryMetaDTO mentorSummary) {
        this.mentorSummary = mentorSummary;
    }

    public OrgApplicationsSummaryMetaDTO getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(OrgApplicationsSummaryMetaDTO studentSummary) {
        this.studentSummary = studentSummary;
    }
}
