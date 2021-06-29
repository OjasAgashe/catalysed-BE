package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "program_participants")
public class ProgramParticipant {
    @EmbeddedId
    private ProgramUserPK id;
    @ManyToOne
    @MapsId("programId")
    @JoinColumn(name = "program_id")
    private Program program;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private AppUser user;
    @ManyToOne
    @MapsId("orgId")
    @JoinColumn(name = "org_id")
    private Organization organization;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    public ProgramUserPK getId() {
        return id;
    }

    public void setId(ProgramUserPK id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
