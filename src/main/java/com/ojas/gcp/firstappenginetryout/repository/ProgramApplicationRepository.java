package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.ProgramApplication;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramApplicationRepository extends JpaRepository<ProgramApplication, Long> {
    List<ProgramApplication> findByProgramIdAndUserType(Long programId, UserType applicantType);

    Optional<ProgramApplication> findByProgramIdAndApplicantId(Long programId, Long userId);

    List<ProgramApplication> findByApplicantIdOrderByAppliedOnDesc(Long applicantId);
}
