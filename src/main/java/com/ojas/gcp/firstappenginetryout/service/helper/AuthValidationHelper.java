package com.ojas.gcp.firstappenginetryout.service.helper;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class AuthValidationHelper {
    private final OrganizationUserRepository orgUserRepository;

    public AuthValidationHelper(OrganizationUserRepository orgUserRepository) {
        this.orgUserRepository = orgUserRepository;
    }

    public void validateSessionUserOrgAccess(SessionUser user, Long orgId) throws ValidationException {
        OrganizationUser sessionOrgUser = getSessionOrgUser(user);
        if (! sessionOrgUser.getOrganization().getId().equals(orgId)) {
            throw new ValidationException("Program doesn't belong to current logged in users org");
        }
    }

    public OrganizationUser getSessionOrgUser(SessionUser user) {
        return orgUserRepository.findById(user.getId()).get();
    }
}
