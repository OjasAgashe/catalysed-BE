package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderMentorDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderOrgDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.profile.ProfileBuilderStudentDTO;

public interface ProfileBuilderService {
    public void setProfile(SessionUser user, ProfileBuilderOrgDTO orgProfileDTO);

    public void setProfile(SessionUser user, ProfileBuilderMentorDTO mentorProfileDTO);

    public void setProfile(SessionUser user, ProfileBuilderStudentDTO studentProfileDTO);

    public ProfileBuilderOrgDTO getOrgProfile(SessionUser user);

    public ProfileBuilderMentorDTO getMentorProfile(SessionUser user);

    public ProfileBuilderStudentDTO getStudentProfile(SessionUser user);
}