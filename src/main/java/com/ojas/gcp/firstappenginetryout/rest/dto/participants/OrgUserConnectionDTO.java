package com.ojas.gcp.firstappenginetryout.rest.dto.participants;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.Organization;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;

public class OrgUserConnectionDTO {
    private Organization org;
    private AppUser user;
    private UserType type;

    public OrgUserConnectionDTO() {
    }

    public OrgUserConnectionDTO(Organization org, AppUser user, UserType type) {
        this.org = org;
        this.user = user;
        this.type = type;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
