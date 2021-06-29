package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;

import javax.xml.bind.ValidationException;

public interface ProfileService {
    OrgProfileDTO getOrgProfile(SessionUser user, Long orgId) throws ValidationException;

    OrgProfileDTO updateOrgProfile(SessionUser user, OrgProfileDTO profileDTO,  Long orgId)  throws ValidationException;
}
