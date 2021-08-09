package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.HomePageSettingsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgUserHomePageDTO;

import javax.xml.bind.ValidationException;

public interface HomePageService {
    HomePageSettingsDTO getHomePageSettings(SessionUser user);

    OrgUserHomePageDTO getOrgUserHomePageDetails(SessionUser user, Long orgId) throws ValidationException;

    OrgUserHomePageDTO getOrgUserInvitationsSummary(SessionUser user, Long orgId) throws ValidationException;

    OrgUserHomePageDTO getOrgUserApplicationsSummary(SessionUser user, Long orgId) throws ValidationException;
}
