package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.ProfileOrgEO;
import com.ojas.gcp.firstappenginetryout.repository.ProfileOrgRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import com.ojas.gcp.firstappenginetryout.service.helper.AuthValidationHelper;

import javax.xml.bind.ValidationException;
import java.util.Optional;

public class ProfileServiceImpl implements ProfileService {
    private final AuthValidationHelper authValidationHelper;
    private final ProfileOrgRepository orgProfileRepository;

    public ProfileServiceImpl(AuthValidationHelper authValidationHelper, ProfileOrgRepository orgProfileRepository) {
        this.authValidationHelper = authValidationHelper;
        this.orgProfileRepository = orgProfileRepository;
    }

    @Override
    public OrgProfileDTO getOrgProfile(SessionUser user, Long orgId) throws ValidationException {
        authValidationHelper.validateSessionUserOrgAccess(user, orgId);
        OrganizationUser orgUser = authValidationHelper.getSessionOrgUser(user);
        ProfileOrgEO orgProfile = getOrgProfileRecord(orgId);
        OrgProfileDTO profileDTO = new OrgProfileDTO(
                orgUser.getEmail(),
                orgUser.getFirstName(),
                orgUser.getLastName(),
                new OrgDetailsProfileDTO(
                        new LocationDTO(orgProfile.getLocationCountry(), orgProfile.getLocationRegion()),
                        orgProfile.getWorkDescription(),
                        new PhoneDTO(orgProfile.getContactPhoneCountryName(), orgProfile.getContactPhoneCountryCode(), orgProfile.getContactPhoneNumber()),
                        orgProfile.getOrganization().getDescription(),
                        orgProfile.getOrganization().getName(),
                        orgProfile.getPrimaryLanguage(),
                        orgProfile.getOrganization().getWebsite(),
                        orgProfile.getOrganization().getSocialMediaCode(),
                        orgProfile.getOrganization().getSocialMediaLink(),
                        orgProfile.getYearOfInception()
                )
        );
        return  profileDTO;
    }

    @Override
    public OrgProfileDTO updateOrgProfile(SessionUser user, OrgProfileDTO profileDTO, Long orgId) throws ValidationException {
        authValidationHelper.validateSessionUserOrgAccess(user, orgId);
        ProfileOrgEO orgProfile = getOrgProfileRecord(orgId);
        updateOrgProfileDetails(orgProfile, profileDTO.getOrganizationDetails());
        updateOrgUserDetailsIfChanged(orgProfile.getOrganization().getOrganizationUser(), profileDTO);
        orgProfileRepository.saveAndFlush(orgProfile);
        return profileDTO;
    }

    private ProfileOrgEO getOrgProfileRecord(Long orgId) throws ValidationException {
        Optional<ProfileOrgEO> orgProfileRecord = orgProfileRepository.findById(orgId);
        if (!orgProfileRecord.isPresent()) {
            throw new ValidationException("Organization Profile is not yet set. Please answer the profile builder questions.");
        }
        return orgProfileRecord.get();
    }

    private void updateOrgUserDetailsIfChanged(OrganizationUser orgUser, OrgProfileDTO profileDTO) {
        if (!orgUser.getFirstName().equals(profileDTO.getFirstName())) {
            orgUser.setFirstName(profileDTO.getFirstName());
        }
        if (!orgUser.getLastName().equals(profileDTO.getLastName())) {
            orgUser.setLastName(profileDTO.getLastName());
        }
    }

    private void updateOrgProfileDetails(ProfileOrgEO orgProfile, OrgDetailsProfileDTO profileDetailsDTO) {
        orgProfile.setPrimaryLanguage(profileDetailsDTO.getPrimaryLanguage());
        orgProfile.setContactPhoneCountryName(profileDetailsDTO.getPhone().getCountryName());
        orgProfile.setContactPhoneCountryCode(profileDetailsDTO.getPhone().getCountryCode());
        orgProfile.setContactPhoneNumber(profileDetailsDTO.getPhone().getNumber());
        orgProfile.setLocationRegion(profileDetailsDTO.getAddress().getRegion());
        orgProfile.setLocationCountry(profileDetailsDTO.getAddress().getCountry());
        orgProfile.setWorkDescription(profileDetailsDTO.getWorkDescription());
        orgProfile.getOrganization().setDescription(profileDetailsDTO.getDescription());
        orgProfile.getOrganization().setSocialMediaCode(profileDetailsDTO.getSocialMediaCode());
        orgProfile.getOrganization().setSocialMediaLink(profileDetailsDTO.getSocialMediaLink());
        orgProfile.getOrganization().setWebsite(profileDetailsDTO.getWebsite());
    }
}
