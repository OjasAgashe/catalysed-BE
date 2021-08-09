package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.connected.ConnectedOrgDetailsForUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import javax.xml.bind.ValidationException;
import java.util.List;

public interface OrganizationService {
    OrganizationDetailsDTO setOrgDetails(SessionUser user, OrganizationDetailsDTO detailsDTO) throws Exception;

    OrgMetaDTO getOrgMetaForOrgUser(SessionUser user) throws ValidationException;

    List<OrgMetaDTO> getConnectedOrgs(Long userId) throws ValidationException;

    ConnectedOrgDetailsForUser getConnectedOrgDetails(Long userId, Long orgId) throws ValidationException;
}
