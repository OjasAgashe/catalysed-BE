package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.HomePageSettingsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgApplicationsSummaryMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgInvitationsSummaryMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgMentorSummaryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgStudentSummaryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.homepage.OrgUserHomePageDTO;
import com.ojas.gcp.firstappenginetryout.service.HomePageService;
import com.ojas.gcp.firstappenginetryout.service.helper.AuthValidationHelper;
import org.springframework.stereotype.Component;
import javax.xml.bind.ValidationException;
import java.util.Optional;

@Component
public class HomePageServiceImpl implements HomePageService {

    private final OrganizationUserRepository organizationUserRepository;
    private final AuthValidationHelper authValidationHelper;

    public HomePageServiceImpl(OrganizationUserRepository organizationUserRepository, AuthValidationHelper authValidationHelper) {
        this.organizationUserRepository = organizationUserRepository;
        this.authValidationHelper = authValidationHelper;
    }

    @Override
    public HomePageSettingsDTO getHomePageSettings(SessionUser user) {
        return null;
    }

    @Override
    public OrgUserHomePageDTO getOrgUserHomePageDetails(SessionUser user, Long orgId) throws ValidationException {
        authValidationHelper.validateSessionUserOrgAccess(user, orgId);
        if (user.getUserType() != UserType.ORGANIZATION_USER) {
            throw new ValidationException("User type not supported");
        }
        Optional<OrganizationUser> orgUserConnectionRecord = organizationUserRepository.findById(user.getId());
        if (!orgUserConnectionRecord.isPresent()) {
            throw new ValidationException("User not linked to any org");
        }
        String orgName = orgUserConnectionRecord.get().getOrganization().getName();
        OrgStudentSummaryDTO studentSummary = new OrgStudentSummaryDTO(20, 10);
        OrgMentorSummaryDTO mentorSummary = new OrgMentorSummaryDTO(10, 6);
        OrgApplicationsSummaryMetaDTO applicationsSummary = new OrgApplicationsSummaryMetaDTO(
                new OrgApplicationsSummaryMetaDTO.SummaryDetails(10, 4),
                new OrgApplicationsSummaryMetaDTO.SummaryDetails(30, 17)
        );
        OrgInvitationsSummaryMetaDTO invitationsSummary = new OrgInvitationsSummaryMetaDTO(30, 17, 13);

        OrgUserHomePageDTO homePageDTO = new OrgUserHomePageDTO(orgName, studentSummary, mentorSummary,
                applicationsSummary, invitationsSummary);
        return homePageDTO;
    }

    @Override
    public OrgUserHomePageDTO getOrgUserInvitationsSummary(SessionUser user, Long orgId) throws ValidationException {
        OrgUserHomePageDTO test = new OrgUserHomePageDTO();
        return test;
    }

    @Override
    public OrgUserHomePageDTO getOrgUserApplicationsSummary(SessionUser user, Long orgId) throws ValidationException {
        OrgUserHomePageDTO test = new OrgUserHomePageDTO();
        return test;
    }
}
