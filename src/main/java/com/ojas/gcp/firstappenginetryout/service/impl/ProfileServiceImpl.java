package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.Organization;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.ProfileOrgEO;
import com.ojas.gcp.firstappenginetryout.entity.ProfileUserEO;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.AppUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProfileOrgRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProfileUserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.ContactDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderMentorDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderOrgDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderStudentDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileServiceImpl implements ProfileService {

    private OrganizationUserRepository orgUserRepository;
    private ProfileOrgRepository profileOrgRepository;
    private ProfileUserRepository profileUserRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public ProfileServiceImpl(OrganizationUserRepository orgUserRepository, ProfileOrgRepository profileOrgRepository,
                              ProfileUserRepository profileUserRepository, AppUserRepository appUserRepository) {
        this.orgUserRepository = orgUserRepository;
        this.profileOrgRepository = profileOrgRepository;
        this.profileUserRepository = profileUserRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void setProfile(SessionUser user, ProfileBuilderOrgDTO orgProfileDTO) {
        AppUser appUser = appUserRepository.findById(user.getId()).get();
        setProfileEO(appUser, orgProfileDTO);
    }

    @Override
    public void setProfile(SessionUser user, ProfileBuilderMentorDTO mentorProfileDTO) {
        AppUser appUser = appUserRepository.findById(user.getId()).get();
        setProfileEO(appUser, mentorProfileDTO);
    }

    @Override
    public void setProfile(SessionUser user, ProfileBuilderStudentDTO studentProfileDTO) {
        AppUser appUser = appUserRepository.findById(user.getId()).get();
        setProfileEO(appUser, studentProfileDTO);
    }

    @Override
    public ProfileBuilderOrgDTO getOrgProfile(SessionUser user) {
        Organization org = orgUserRepository.findById(user.getId()).get().getOrganization();
        ProfileOrgEO profileOrgEO = profileOrgRepository.findById(org.getId()).get();
        return getProfileDTO(profileOrgEO);
    }

    @Override
    public ProfileBuilderMentorDTO getMentorProfile(SessionUser user) {
        ProfileUserEO profileUserEO = profileUserRepository.findById(user.getId()).get();
        return getProfileMentorDTO(profileUserEO);
    }

    @Override
    public ProfileBuilderStudentDTO getStudentProfile(SessionUser user) {
        ProfileUserEO profileUserEO = profileUserRepository.findById(user.getId()).get();
        return getProfileStudentDTO(profileUserEO);
    }

    private ProfileBuilderOrgDTO getProfileDTO(ProfileOrgEO profileOrgEO) {
        return new ProfileBuilderOrgDTO(
                new ContactDetailsDTO(
                        profileOrgEO.getContactEmail(),
                        new PhoneDTO(profileOrgEO.getContactPhoneCountryName(), profileOrgEO.getContactPhoneCountryCode(), profileOrgEO.getContactPhoneNumber())
                ),
                new LocationDTO(profileOrgEO.getLocationCountry(), profileOrgEO.getLocationRegion()),
                profileOrgEO.getPrimaryLanguage(),
                profileOrgEO.getWorkDescription(),
                profileOrgEO.getYearOfInception()
        );
    }

    private ProfileBuilderMentorDTO getProfileMentorDTO(ProfileUserEO profileUserEO) {
        return new ProfileBuilderMentorDTO(
                new ContactDetailsDTO(
                        profileUserEO.getContactEmail(),
                        new PhoneDTO(profileUserEO.getContactPhoneCountryName(), profileUserEO.getContactPhoneCountryCode(), profileUserEO.getContactPhoneNumber())
                ),
                profileUserEO.getPrimaryLanguage(),
                new LocationDTO(profileUserEO.getLocationCountry(), profileUserEO.getLocationRegion()),
                profileUserEO.getBirthYear(),
                profileUserEO.getOrganization(),
                profileUserEO.getGender(),
                profileUserEO.getMentorQualification(),
                profileUserEO.getMentorProfession(),
                profileUserEO.isMentorPreviouslyMentored(),
                profileUserEO.isStableConnection()
        );
    }

    private ProfileBuilderStudentDTO getProfileStudentDTO(ProfileUserEO profileUserEO) {
        return new ProfileBuilderStudentDTO(
                new ContactDetailsDTO(
                        profileUserEO.getContactEmail(),
                        new PhoneDTO(profileUserEO.getContactPhoneCountryName(), profileUserEO.getContactPhoneCountryCode(), profileUserEO.getContactPhoneNumber())
                ),
                new LocationDTO(profileUserEO.getLocationCountry(), profileUserEO.getLocationRegion()),
                profileUserEO.getPrimaryLanguage(),
                profileUserEO.getBirthYear(),
                profileUserEO.getOrganization(),
                profileUserEO.getGender(),
                profileUserEO.isStableConnection(),
                profileUserEO.getPrimaryDevice(),
                profileUserEO.isStudentPreviouslyMentored()
        );
    }

    private void setProfileEO(AppUser user, ProfileBuilderOrgDTO orgDTO) {
        ProfileOrgEO orgEO = new ProfileOrgEO();
        OrganizationUser orgUser = orgUserRepository.findById(user.getId()).get();
        orgEO.setOrganization(orgUser.getOrganization());
        orgEO.setWorkDescription(orgDTO.getWorkDescription());
        orgEO.setContactEmail(orgDTO.getContactDetails().getEmail());
        orgEO.setContactPhoneCountryName(orgDTO.getContactDetails().getPhone().getCountryName());
        orgEO.setContactPhoneCountryCode(orgDTO.getContactDetails().getPhone().getCountryCode());
        orgEO.setContactPhoneNumber(orgDTO.getContactDetails().getPhone().getNumber());
        orgEO.setLocationCountry(orgDTO.getLocation().getCountry());
        orgEO.setLocationRegion(orgDTO.getLocation().getRegion());
        orgEO.setYearOfInception(orgDTO.getYearOfInception());
        orgEO.setPrimaryLanguage(orgDTO.getPrimaryLanguage());
        profileOrgRepository.saveAndFlush(orgEO);
    }

    private void setProfileEO(AppUser user, ProfileBuilderMentorDTO mentorDTO) {
        ProfileUserEO userEO = new ProfileUserEO();
        userEO.setUser(user);
        userEO.setUserType(UserType.MENTOR);
        userEO.setContactEmail(mentorDTO.getContactDetails().getEmail());
        userEO.setContactPhoneCountryName(mentorDTO.getContactDetails().getPhone().getCountryName());
        userEO.setContactPhoneCountryCode(mentorDTO.getContactDetails().getPhone().getCountryCode());
        userEO.setContactPhoneNumber(mentorDTO.getContactDetails().getPhone().getNumber());
        userEO.setLocationCountry(mentorDTO.getLocation().getCountry());
        userEO.setLocationRegion(mentorDTO.getLocation().getRegion());
        userEO.setBirthYear(mentorDTO.getBirthYear());
        userEO.setOrganization(mentorDTO.getOrganization());
        userEO.setGender(mentorDTO.getGender());
        userEO.setPrimaryLanguage(mentorDTO.getPrimaryLanguage());
        userEO.setMentorQualification(mentorDTO.getQualification());
        userEO.setMentorProfession(mentorDTO.getProfession());
        userEO.setMentorPreviouslyMentored(mentorDTO.isPreviouslyMentored());
        userEO.setStableConnection(mentorDTO.isStableConnection());
        profileUserRepository.saveAndFlush(userEO);
    }

    private void setProfileEO(AppUser user, ProfileBuilderStudentDTO studentDTO) {
        ProfileUserEO userEO = new ProfileUserEO();
        userEO.setUser(user);
        userEO.setUserType(UserType.STUDENT);
        userEO.setContactEmail(studentDTO.getContactDetails().getEmail());
        userEO.setContactPhoneCountryName(studentDTO.getContactDetails().getPhone().getCountryName());
        userEO.setContactPhoneCountryCode(studentDTO.getContactDetails().getPhone().getCountryCode());
        userEO.setContactPhoneNumber(studentDTO.getContactDetails().getPhone().getNumber());
        userEO.setLocationCountry(studentDTO.getLocation().getCountry());
        userEO.setLocationRegion(studentDTO.getLocation().getRegion());
        userEO.setBirthYear(studentDTO.getBirthYear());
        userEO.setOrganization(studentDTO.getOrganization());
        userEO.setGender(studentDTO.getGender());
        userEO.setPrimaryLanguage(studentDTO.getPrimaryLanguage());
        userEO.setStudentPreviouslyMentored(studentDTO.isPreviouslyMentored());
        userEO.setPrimaryDevice(studentDTO.getPrimaryDevice());
        userEO.setStableConnection(studentDTO.isStableConnection());
        profileUserRepository.saveAndFlush(userEO);
    }
}
