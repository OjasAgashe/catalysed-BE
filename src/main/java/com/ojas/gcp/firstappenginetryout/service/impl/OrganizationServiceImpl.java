package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.*;
import com.ojas.gcp.firstappenginetryout.entity.enums.OrgSubscriptionType;
import com.ojas.gcp.firstappenginetryout.repository.OrgUserConnectionRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.connected.ConnectedOrgDetailsForUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import com.ojas.gcp.firstappenginetryout.service.OrganizationService;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import com.ojas.gcp.firstappenginetryout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationUserRepository orgUserRepository;
    private final OrgUserConnectionRepository orgUserConnectionRepository;
    private final ProfileService profileService;
    private final ProgramService programService;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationUserRepository orgUserRepository,
                                   OrgUserConnectionRepository orgUserConnectionRepository, ProfileService profileService,
                                   ProgramService programService) {
        this.organizationRepository = organizationRepository;
        this.orgUserRepository = orgUserRepository;
        this.orgUserConnectionRepository = orgUserConnectionRepository;
        this.profileService = profileService;
        this.programService = programService;
    }

    @Override
    public OrganizationDetailsDTO setOrgDetails(SessionUser user, OrganizationDetailsDTO detailsDTO) throws Exception {
        Organization organization = null;
        //check if org is set-up and verified
        //check if the user trying to create an org already has an org assigned to him/her
        if (detailsDTO.getId() == null) { // && AppUser.getOrganizations().isEmpty()
            organization = mapDTOToEntity(user, detailsDTO);
            organizationRepository.saveAndFlush(organization);
        } else {
            //update org details case
            Optional<Organization> dbRecord= organizationRepository.findById(detailsDTO.getId());
            dbRecord.orElseThrow(() -> new Exception("Organization : " + detailsDTO.getName() + "not found "));
            organization = dbRecord.get();

            organization.setDescription(detailsDTO.getDescription());
            organization.setOrgLogo(detailsDTO.getOrgLogo());
//            organization.setSocialMediaLink(detailsDTO.getSocialMediaLink());
            organizationRepository.saveAndFlush(organization);
        }
        return mapEntityToDTO(organization);
    }

    @Override
    public OrgMetaDTO getOrgMetaForOrgUser(SessionUser user) throws ValidationException {
        Organization organization = orgUserRepository.findById(user.getId()).get().getOrganization();
        return buildOrgMetaDTO(organization);
    }

    @Override
    public List<OrgMetaDTO> getConnectedOrgs(Long userId) throws ValidationException {
        List<OrgUserConnection> orgUserConnection = orgUserConnectionRepository.findByIdUserId(userId);
        if(CollectionUtils.isEmpty(orgUserConnection)) {
            throw new ValidationException("User not yet connected with any Organization");
        }
        return orgUserConnection.stream()
                .map(orgConnection -> buildOrgMetaDTO(orgConnection.getOrganization()))
                .collect(Collectors.toList());
    }

    @Override
    public ConnectedOrgDetailsForUser getConnectedOrgDetails(Long userId, Long orgId) throws ValidationException {
        Optional<OrgUserConnection> orgUserConnection = orgUserConnectionRepository.findByIdOrgIdAndIdUserId(orgId, userId);
        if(!orgUserConnection.isPresent()) {
            throw new ValidationException("User not yet connected with Organization");
        }
        return buildConnectedOrgDetailsForUser(orgId);
    }

    private Organization mapDTOToEntity(SessionUser user, OrganizationDetailsDTO detailsDTO) {
        Organization organization = new Organization();
        organization.setName(detailsDTO.getName());
        organization.setDescription(detailsDTO.getDescription());
        organization.setOrgLogo(detailsDTO.getOrgLogo());
        organization.setWebsite(detailsDTO.getWebsite());
        organization.setSocialMediaLink(detailsDTO.getSocialMediaLink());
        organization.setVerified(false);
        organization.setOrganizationUser(getOrganizationUser(user.getId()));
        organization.setSubscription(OrgSubscriptionType.BASIC.getValue());
        return organization;
    }

    private OrganizationDetailsDTO mapEntityToDTO(Organization org) {
        OrganizationDetailsDTO orgDTO = new OrganizationDetailsDTO();
        orgDTO.setId(org.getId());
        orgDTO.setName(org.getName());
        orgDTO.setDescription(org.getDescription());
        orgDTO.setOrgLogo(org.getOrgLogo());
        orgDTO.setWebsite(org.getWebsite());
        orgDTO.setSocialMediaLink(org.getSocialMediaLink());
        orgDTO.setVerified(org.getVerified());
        orgDTO.setSubscription(OrgSubscriptionType.BASIC.getValue());
        return orgDTO;
    }

    private OrganizationUser getOrganizationUser(Long id) {
        Optional<OrganizationUser> orgUser = orgUserRepository.findById(id);
        orgUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return orgUser.get();
    }

    private ConnectedOrgDetailsForUser buildConnectedOrgDetailsForUser(Long orgId) throws ValidationException {
        return new ConnectedOrgDetailsForUser(
                profileService.getOrgDetailsProfileForNonOrgUser(orgId),
                programService.getProgramsMetaForOrgById(orgId)
        );
    }

    private OrgMetaDTO buildOrgMetaDTO(Organization organization) {
        return new OrgMetaDTO(
                organization.getId(),
                organization.getName(),
                organization.getDescription(),
                organization.getOrgLogo(),
                organization.getWebsite(),
                organization.getSocialMediaCode(),
                organization.getSocialMediaLink()
                );
    }
}
