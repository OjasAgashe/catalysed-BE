package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgUserHomePageDTO {
    private String orgName;
    private OrgStudentSummaryDTO studentSummary;
    private OrgMentorSummaryDTO mentorSummary;
    private OrgApplicationsSummaryMetaDTO applicationsSummary;
    private OrgInvitationsSummaryMetaDTO invitationsSummary;

    public OrgUserHomePageDTO() {

    }

    public OrgUserHomePageDTO(String orgName, OrgStudentSummaryDTO studentSummary,
                              OrgMentorSummaryDTO mentorSummary, OrgApplicationsSummaryMetaDTO applicationsSummary,
                              OrgInvitationsSummaryMetaDTO invitationsSummary) {
        this.orgName = orgName;
        this.studentSummary = studentSummary;
        this.mentorSummary = mentorSummary;
        this.applicationsSummary = applicationsSummary;
        this.invitationsSummary = invitationsSummary;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public OrgStudentSummaryDTO getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(OrgStudentSummaryDTO studentSummary) {
        this.studentSummary = studentSummary;
    }

    public OrgMentorSummaryDTO getMentorSummary() {
        return mentorSummary;
    }

    public void setMentorSummary(OrgMentorSummaryDTO mentorSummary) {
        this.mentorSummary = mentorSummary;
    }

    public OrgApplicationsSummaryMetaDTO getApplicationsSummary() {
        return applicationsSummary;
    }

    public void setApplicationsSummary(OrgApplicationsSummaryMetaDTO applicationsSummary) {
        this.applicationsSummary = applicationsSummary;
    }

    public OrgInvitationsSummaryMetaDTO getInvitationsSummary() {
        return invitationsSummary;
    }

    public void setInvitationsSummary(OrgInvitationsSummaryMetaDTO invitationsSummary) {
        this.invitationsSummary = invitationsSummary;
    }
}
