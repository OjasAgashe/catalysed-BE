package com.ojas.gcp.firstappenginetryout.entity;

import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.InvitationType;

import javax.persistence.*;

@Entity
@Table(name = "app_invitations")
public class AppInvitation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="org_id", nullable=false)
    private Organization organization;
    @Column(name = "invitation_key")
    private String invitationKey;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private InvitationType type;
    @Column(name = "program_id")
    private Long programId;
    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private InvitationStatus status;

    public AppInvitation() {
    }

    public AppInvitation(Organization organization, String invitationKey, String email, InvitationType type,
                         Long programId, InvitationStatus status) {
        this.organization = organization;
        this.invitationKey = invitationKey;
        this.email = email;
        this.type = type;
        this.programId = programId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getInvitationKey() {
        return invitationKey;
    }

    public void setInvitationKey(String invitationKey) {
        this.invitationKey = invitationKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InvitationType getType() {
        return type;
    }

    public void setType(InvitationType type) {
        this.type = type;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
