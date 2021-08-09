package com.ojas.gcp.firstappenginetryout.rest.dto.invitations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramInvitationDTO {
    private Long id;
    private Long programId;
    private Long userId;
    private String emailId;
    private String name;
    private UserType userType;
    private InvitationStatus responseStatus;
    private String subject;
    private String message;
    private String sentAt;

    public ProgramInvitationDTO() {
    }

    public ProgramInvitationDTO(Long id, Long programId, Long userId, String emailId, String name, UserType userType,
                                InvitationStatus responseStatus, String subject, String message, String sentAt) {
        this.id = id;
        this.programId = programId;
        this.userId = userId;
        this.emailId = emailId;
        this.name = name;
        this.userType = userType;
        this.responseStatus = responseStatus;
        this.subject = subject;
        this.message = message;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public InvitationStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(InvitationStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
