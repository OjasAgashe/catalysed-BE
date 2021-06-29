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
}
