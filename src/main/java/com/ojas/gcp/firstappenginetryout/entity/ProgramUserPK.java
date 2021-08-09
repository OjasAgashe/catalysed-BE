package com.ojas.gcp.firstappenginetryout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProgramUserPK implements Serializable {
    @Column(name = "program_id")
    private Long programId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "org_id")
    private Long orgId;

    public ProgramUserPK() {

    }

    public ProgramUserPK(Long programId, Long userId, Long orgId) {
        this.programId = programId;
        this.userId = userId;
        this.orgId = orgId;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
