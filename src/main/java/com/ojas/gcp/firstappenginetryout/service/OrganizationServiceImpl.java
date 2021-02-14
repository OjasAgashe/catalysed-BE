package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.Organization;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.OrgSubscriptionType;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrganizationDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationRepository organizationRepository;
    private final OrganizationUserRepository orgUserRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationUserRepository orgUserRepository) {
        this.organizationRepository = organizationRepository;
        this.orgUserRepository = orgUserRepository;
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
}
