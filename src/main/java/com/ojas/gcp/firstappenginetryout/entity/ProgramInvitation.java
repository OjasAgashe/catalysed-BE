package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "program_invitations")
public class ProgramInvitation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    @JsonBackReference
    private Program program;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser recipient;
    @ManyToOne
    @JoinColumn(name = "invitation_details_id")
    @JsonBackReference
    private ProgramInvitationDetails invitationDetails;
    @Column(name = "email_id", nullable=false)
    private String emailId;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable=false)
    private UserType userType;
    @Column(name = "sent_at")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = )
//    private LocalDateTime sentAt;
    private String sentAt;
    @Enumerated(EnumType.STRING)
    @Column(name="response_status")
    private InvitationStatus responseStatus;
    //created By
    //create At


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public AppUser getRecipient() {
        return recipient;
    }

    public void setRecipient(AppUser recipient) {
        this.recipient = recipient;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public InvitationStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(InvitationStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ProgramInvitationDetails getInvitationDetails() {
        return invitationDetails;
    }

    public void setInvitationDetails(ProgramInvitationDetails invitationDetails) {
        this.invitationDetails = invitationDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

