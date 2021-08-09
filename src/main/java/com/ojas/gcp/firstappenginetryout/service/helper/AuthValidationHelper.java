package com.ojas.gcp.firstappenginetryout.service.helper;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import com.ojas.gcp.firstappenginetryout.entity.Student;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.MentorRepository;
import com.ojas.gcp.firstappenginetryout.repository.OrganizationUserRepository;
import com.ojas.gcp.firstappenginetryout.repository.StudentRepository;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class AuthValidationHelper {
    private final OrganizationUserRepository orgUserRepository;
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;


    public AuthValidationHelper(OrganizationUserRepository orgUserRepository, StudentRepository studentRepository,
                                MentorRepository mentorRepository) {
        this.orgUserRepository = orgUserRepository;
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
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
