package com.ojas.gcp.firstappenginetryout.rest.dto.invitations;

import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;

public class UserViewProgramInviteMetaDTO {
    private String programName;
    private String orgName;
    private String sentAt;
    private String tentativeProgramStartDate;
    private InvitationStatus invitationResponseStatus;

    public UserViewProgramInviteMetaDTO() {

    }

    public UserViewProgramInviteMetaDTO(String programName, String orgName, String sentAt,
                                        String tentativeProgramStartDate, InvitationStatus invitationResponseStatus) {
        this.programName = programName;
        this.orgName = orgName;
        this.sentAt = sentAt;
        this.tentativeProgramStartDate = tentativeProgramStartDate;
        this.invitationResponseStatus = invitationResponseStatus;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public String getTentativeProgramStartDate() {
        return tentativeProgramStartDate;
    }

    public void setTentativeProgramStartDate(String tentativeProgramStartDate) {
        this.tentativeProgramStartDate = tentativeProgramStartDate;
    }

    public InvitationStatus getInvitationResponseStatus() {
        return invitationResponseStatus;
    }

    public void setInvitationResponseStatus(InvitationStatus invitationResponseStatus) {
        this.invitationResponseStatus = invitationResponseStatus;
    }
}
