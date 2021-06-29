package com.ojas.gcp.firstappenginetryout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrgUserPK implements Serializable {
    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "user_id")
    private Long userId;
}
