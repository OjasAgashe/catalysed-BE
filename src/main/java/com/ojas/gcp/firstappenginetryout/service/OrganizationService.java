package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;

public interface OrganizationService {

    public OrganizationDetailsDTO setOrgDetails(SessionUser user, OrganizationDetailsDTO detailsDTO) throws Exception;
}
