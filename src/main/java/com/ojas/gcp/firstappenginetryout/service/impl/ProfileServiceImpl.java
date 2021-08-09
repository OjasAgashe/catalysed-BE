package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.ProfileOrgEO;
import com.ojas.gcp.firstappenginetryout.entity.ProfileUserEO;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.ProfileOrgRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProfileUserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.OrgMetaDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.MentorProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgDetailsProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.OrgProfileDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.StudentProfileDTO;
import com.ojas.gcp.firstappenginetryout.service.ProfileService;
import com.ojas.gcp.firstappenginetryout.service.helper.AuthValidationHelper;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

import static com.ojas.gcp.firstappenginetryout.entity.enums.UserType.ORGANIZATION_USER;

@Component
public class ProfileServiceImpl implements ProfileService {
    private final AuthValidationHelper authValidationHelper;
    private final ProfileOrgRepository orgProfileRepository;
    private final ProfileUserRepository userProfileRepository;

    public ProfileServiceImpl(AuthValidationHelper authValidationHelper, ProfileOrgRepository orgProfileRepository,
                              ProfileUserRepository userProfileRepository) {
        this.authValidationHelper = authValidationHelper;
        this.orgProfileRepository = orgProfileRepository;
        this.userProfileRepository = userProfileRepository;
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
                buildOrgProfileDetailsDTO(orgProfile)
        );
        return  profileDTO;
    }

    @Override
    public OrgDetailsProfileDTO getOrgDetailsProfileForNonOrgUser(Long orgId) throws ValidationException {
        ProfileOrgEO orgProfile = getOrgProfileRecord(orgId);
        return buildOrgProfileDetailsDTO(orgProfile);
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

    @Override
    public StudentProfileDTO updateStudentProfile(SessionUser user, StudentProfileDTO profileDTO, Long studentId)  throws ValidationException {
        ProfileUserEO userProfile = getUserProfileRecord(studentId);
        updateStudentProfileDetails(userProfile, profileDTO);
        userProfileRepository.saveAndFlush(userProfile);
        return profileDTO;
    }

    @Override
    public MentorProfileDTO updateMentorProfile(SessionUser user, MentorProfileDTO profileDTO, Long mentorId)  throws ValidationException {
        ProfileUserEO userProfile = getUserProfileRecord(mentorId);
        updateMentorProfileDetails(userProfile, profileDTO);
        userProfileRepository.saveAndFlush(userProfile);
        return profileDTO;
    }

    @Override
    public StudentProfileDTO getStudentProfile(SessionUser user, Long studentId)  throws ValidationException {
        ProfileUserEO userProfile = getUserProfileRecord(studentId);
        return getStudentProfileDTO(userProfile);
    }

    @Override
    public MentorProfileDTO getMentorProfile(SessionUser user, Long mentorId)  throws ValidationException {
        ProfileUserEO userProfile = getUserProfileRecord(mentorId);
        return getMentorProfileDTO(userProfile);
    }

    private ProfileOrgEO getOrgProfileRecord(Long orgId) throws ValidationException {
        Optional<ProfileOrgEO> orgProfileRecord = orgProfileRepository.findById(orgId);
        if (!orgProfileRecord.isPresent()) {
            throw new ValidationException("Organization Profile is not yet set. Please answer the profile builder questions.");
        }
        return orgProfileRecord.get();
    }

    private ProfileUserEO getUserProfileRecord(Long userId) throws ValidationException {
        Optional<ProfileUserEO> userProfileRecord = userProfileRepository.findById(userId);
        if (!userProfileRecord.isPresent()) {
            throw new ValidationException("Profile is not yet set. Please answer the profile builder questions.");
        }
        return userProfileRecord.get();
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
        orgProfile.setYearOfInception(profileDetailsDTO.getYearOfInception());
        orgProfile.getOrganization().setDescription(profileDetailsDTO.getDescription());
        orgProfile.getOrganization().setSocialMediaCode(profileDetailsDTO.getSocialMediaCode());
        orgProfile.getOrganization().setSocialMediaLink(profileDetailsDTO.getSocialMediaLink());
        orgProfile.getOrganization().setWebsite(profileDetailsDTO.getWebsite());
    }

    private void updateStudentProfileDetails(ProfileUserEO userProfile, StudentProfileDTO studentProfileDTO) {
//        userProfile.getUser().setFirstName(studentProfileDTO.getFirstName());
//        userProfile.getUser().setLastName(studentProfileDTO.getLastName());
        userProfile.setBirthYear(studentProfileDTO.getBirthYear());
        userProfile.setOrganization(studentProfileDTO.getOrganization());
        userProfile.setGender(studentProfileDTO.getGender());
        userProfile.setStableConnection(studentProfileDTO.isStableConnection());
        userProfile.setStudentPreviouslyMentored(studentProfileDTO.isPreviouslyMentored());
        userProfile.setPrimaryDevice(studentProfileDTO.getPrimaryDevice());
        userProfile.setPrimaryLanguage(studentProfileDTO.getPrimaryLanguage());
        userProfile.setLocationCountry(studentProfileDTO.getLocation().getCountry());
        userProfile.setLocationRegion(studentProfileDTO.getLocation().getRegion());
        userProfile.setContactPhoneCountryCode(studentProfileDTO.getPhone().getCountryCode());
        userProfile.setContactPhoneCountryName(studentProfileDTO.getPhone().getCountryName());
        userProfile.setContactPhoneNumber(studentProfileDTO.getPhone().getNumber());
    }

    private StudentProfileDTO getStudentProfileDTO(ProfileUserEO userProfile) {
        StudentProfileDTO student = new StudentProfileDTO(
                userProfile.getId(),
                userProfile.getContactEmail(),
                userProfile.getUser().getFirstName(),
                userProfile.getUser().getLastName(),
                userProfile.getBirthYear(),
                userProfile.getOrganization(),
                userProfile.getGender(),
                userProfile.getPrimaryLanguage(),
                new LocationDTO(userProfile.getLocationCountry(), userProfile.getLocationRegion()),
                new PhoneDTO(userProfile.getContactPhoneCountryName(), userProfile.getContactPhoneCountryCode(), userProfile.getContactPhoneNumber()),
                userProfile.isStableConnection(),
                userProfile.getPrimaryDevice(),
                userProfile.isStudentPreviouslyMentored()
        );
        return student;
    }

    private MentorProfileDTO getMentorProfileDTO(ProfileUserEO userProfile) {
        MentorProfileDTO mentor = new MentorProfileDTO(
                userProfile.getId(),
                userProfile.getContactEmail(),
                userProfile.getUser().getFirstName(),
                userProfile.getUser().getLastName(),
                userProfile.getBirthYear(),
                userProfile.getOrganization(),
                userProfile.getGender(),
                userProfile.getPrimaryLanguage(),
                new LocationDTO(userProfile.getLocationCountry(), userProfile.getLocationRegion()),
                new PhoneDTO(userProfile.getContactPhoneCountryName(), userProfile.getContactPhoneCountryCode(), userProfile.getContactPhoneNumber()),
                userProfile.getMentorQualification(),
                userProfile.getMentorProfession(),
                userProfile.isStableConnection(),
                userProfile.isMentorPreviouslyMentored(),
                userProfile.getMentorExperience()
        );
        return mentor;
    }

    private void updateMentorProfileDetails(ProfileUserEO userProfile, MentorProfileDTO mentorProfileDTO) {
//        userProfile.getUser().setFirstName(mentorProfileDTO.getFirstName());
//        userProfile.getUser().setLastName(mentorProfileDTO.getLastName());
        userProfile.setBirthYear(mentorProfileDTO.getBirthYear());
        userProfile.setOrganization(mentorProfileDTO.getOrganization());
        userProfile.setGender(mentorProfileDTO.getGender());
        userProfile.setMentorQualification(mentorProfileDTO.getQualification());
        userProfile.setMentorProfession(mentorProfileDTO.getProfession());
        userProfile.setStableConnection(mentorProfileDTO.isStableConnection());
        userProfile.setMentorPreviouslyMentored(mentorProfileDTO.isPreviouslyMentored());
        userProfile.setMentorExperience(mentorProfileDTO.getExperience());
        userProfile.setPrimaryLanguage(mentorProfileDTO.getPrimaryLanguage());
        userProfile.setLocationCountry(mentorProfileDTO.getLocation().getCountry());
        userProfile.setLocationRegion(mentorProfileDTO.getLocation().getRegion());
        userProfile.setContactPhoneCountryCode(mentorProfileDTO.getPhone().getCountryCode());
        userProfile.setContactPhoneCountryName(mentorProfileDTO.getPhone().getCountryName());
        userProfile.setContactPhoneNumber(mentorProfileDTO.getPhone().getNumber());
    }

    private OrgDetailsProfileDTO buildOrgProfileDetailsDTO(ProfileOrgEO orgProfile) {
        return new OrgDetailsProfileDTO(
                new LocationDTO(orgProfile.getLocationCountry(), orgProfile.getLocationRegion()),
                orgProfile.getWorkDescription(),
                new PhoneDTO(orgProfile.getContactPhoneCountryName(), orgProfile.getContactPhoneCountryCode(), orgProfile.getContactPhoneNumber()),
                orgProfile.getContactEmail(),
                orgProfile.getOrganization().getDescription(),
                orgProfile.getOrganization().getName(),
                orgProfile.getPrimaryLanguage(),
                orgProfile.getOrganization().getWebsite(),
                orgProfile.getOrganization().getSocialMediaCode(),
                orgProfile.getOrganization().getSocialMediaLink(),
                orgProfile.getYearOfInception()
        );
    }
}
