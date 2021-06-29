package com.ojas.gcp.firstappenginetryout.service.impl;

import com.ojas.gcp.firstappenginetryout.entity.OrgUserConnection;
import com.ojas.gcp.firstappenginetryout.entity.ProfileUserEO;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.OrgUserConnectionRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProfileUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.ProgramParticipantRepository;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramLite;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramTitle;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgMentorDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgStudentDirectoryDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgUserConnectedProgramDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.directory.OrgUserDirectoryMetaDTO;
import com.ojas.gcp.firstappenginetryout.service.DirectoryService;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    private OrgUserConnectionRepository connectionRepository;
    private ProgramParticipantRepository programParticipantRepository;
    private ProfileUserRepository profileRepository;

    public DirectoryServiceImpl(OrgUserConnectionRepository connectionRepository,
                                ProgramParticipantRepository programParticipantRepository, ProfileUserRepository profileRepository) {
        this.connectionRepository = connectionRepository;
        this.programParticipantRepository = programParticipantRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<OrgUserDirectoryMetaDTO> getOrgStudentConnections(Long orgId) throws ValidationException {
        return buildOrgUserDirectoryMetaDTO(orgId, UserType.STUDENT);
    }

    @Override
    public OrgStudentDirectoryDTO getConnectedStudent(Long orgId, Long studentId) throws ValidationException {
        List<ParticipantProgramLite> programLiteList = programParticipantRepository.findByIdUserIdAndIdOrgIdOrderByProgramDesc(studentId, orgId);
        List<OrgUserConnectedProgramDTO> connectPrograms = programLiteList.stream()
                .map(m -> new OrgUserConnectedProgramDTO(m.getId(), m.getStatus(), m.getTitle()))
                .collect(Collectors.toList());
        Optional<ProfileUserEO> studentProfileOptional = profileRepository.findById(studentId);
        if (!studentProfileOptional.isPresent()) {
            throw new ValidationException("User profile not yet set by the Student");
        }
        ProfileUserEO studentProfile = studentProfileOptional.get();
        OrgStudentDirectoryDTO student = new OrgStudentDirectoryDTO(
                studentProfile.getId(),
                studentProfile.getUser().getFirstName(),
                studentProfile.getUser().getLastName(),
                studentProfile.getUser().getEmail(),
                new PhoneDTO(studentProfile.getContactPhoneCountryName(), studentProfile.getContactPhoneCountryCode(), studentProfile.getContactPhoneNumber()),
                new LocationDTO(studentProfile.getLocationCountry(), studentProfile.getLocationRegion()),
                studentProfile.getPrimaryLanguage(),
                studentProfile.getBirthYear(),
                studentProfile.getOrganization(),
                studentProfile.getGender(),
                studentProfile.isStableConnection(),
                studentProfile.getPrimaryDevice(),
                studentProfile.isStudentPreviouslyMentored(),
                connectPrograms == null ? new ArrayList<>() : connectPrograms
        );
        return student;
    }

    @Override
    public List<OrgUserDirectoryMetaDTO> getOrgMentorConnections(Long orgId) throws ValidationException {
        return buildOrgUserDirectoryMetaDTO(orgId, UserType.MENTOR);
    }

    @Override
    public OrgMentorDirectoryDTO getConnectedMentor(Long orgId, Long mentorId) throws ValidationException {
        List<ParticipantProgramLite> programLiteList = programParticipantRepository.findByIdUserIdAndIdOrgIdOrderByProgramDesc(mentorId, orgId);
        List<OrgUserConnectedProgramDTO> connectPrograms = programLiteList.stream()
                .map(m -> new OrgUserConnectedProgramDTO(m.getId(), m.getStatus(), m.getTitle()))
                .collect(Collectors.toList());
        Optional<ProfileUserEO> mentorProfileOptional = profileRepository.findById(mentorId);
        if (!mentorProfileOptional.isPresent()) {
            throw new ValidationException("User profile not yet set by the Student");
        }
        ProfileUserEO mentorProfile = mentorProfileOptional.get();
        OrgMentorDirectoryDTO mentor = new OrgMentorDirectoryDTO(
                mentorProfile.getId(),
                mentorProfile.getUser().getFirstName(),
                mentorProfile.getUser().getLastName(),
                mentorProfile.getUser().getEmail(),
                new PhoneDTO(mentorProfile.getContactPhoneCountryName(), mentorProfile.getContactPhoneCountryCode(), mentorProfile.getContactPhoneNumber()),
                new LocationDTO(mentorProfile.getLocationCountry(), mentorProfile.getLocationRegion()),
                mentorProfile.getPrimaryLanguage(),
                mentorProfile.getBirthYear(),
                mentorProfile.getOrganization(),
                mentorProfile.getGender(),
                mentorProfile.getMentorQualification(),
                mentorProfile.getMentorProfession(),
                mentorProfile.isStableConnection(),
                mentorProfile.isMentorPreviouslyMentored(),
                mentorProfile.getMentorExperience(),
                connectPrograms == null ? new ArrayList<>() : connectPrograms
        );
        return mentor;
    }

    private List<OrgUserDirectoryMetaDTO> buildOrgUserDirectoryMetaDTO(Long orgId, UserType type) {
        List<OrgUserConnection> orgUserConnections = connectionRepository.findByIdOrgIdAndUserType(orgId, type);
        List<OrgUserDirectoryMetaDTO> result = new ArrayList<>();
        orgUserConnections.forEach(connection -> {
            List<ParticipantProgramTitle> participantProgramTitles = programParticipantRepository
                    .findTop3ByIdUserIdAndIdOrgIdOrderByProgramDesc(connection.getUser().getId(), orgId);
            OrgUserDirectoryMetaDTO connectedUser = new OrgUserDirectoryMetaDTO(
                    connection.getUser().getId(),
                    connection.getUser().getFirstName() + " " + connection.getUser().getLastName(),
                    connection.getUser().getEmail(),
                    participantProgramTitles.stream().map(m -> m.getTitle()).collect(Collectors.toList()),
                    connection.getUserType()
            );
            result.add(connectedUser);
        });
        return result;
    }
}
