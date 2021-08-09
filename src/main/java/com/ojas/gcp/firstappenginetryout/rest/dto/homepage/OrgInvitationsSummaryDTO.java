package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgInvitationsSummaryDTO {
    private String programName;
    private OrgInvitationsSummaryMetaDTO mentorSummary;
    private OrgInvitationsSummaryMetaDTO studentSummary;

    public OrgInvitationsSummaryDTO() {

    }

    public OrgInvitationsSummaryDTO(String programName, OrgInvitationsSummaryMetaDTO mentorSummary,
                                    OrgInvitationsSummaryMetaDTO studentSummary) {
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

    public OrgInvitationsSummaryMetaDTO getMentorSummary() {
        return mentorSummary;
    }

    public void setMentorSummary(OrgInvitationsSummaryMetaDTO mentorSummary) {
        this.mentorSummary = mentorSummary;
    }

    public OrgInvitationsSummaryMetaDTO getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(OrgInvitationsSummaryMetaDTO studentSummary) {
        this.studentSummary = studentSummary;
    }
}
