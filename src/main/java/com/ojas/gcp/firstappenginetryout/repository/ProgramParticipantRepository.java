package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.ProgramParticipant;
import com.ojas.gcp.firstappenginetryout.entity.ProgramUserPK;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramLite;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgramParticipantRepository extends JpaRepository<ProgramParticipant, ProgramUserPK> {
    List<ProgramParticipant> findByIdProgramId(Long programId);

    List<ProgramParticipant> findByIdProgramIdAndUserType(Long programId, UserType userType);

    List<ParticipantProgramTitle> findTop3ByIdUserIdAndIdOrgIdOrderByProgramDesc(Long userId, Long orgId);

    List<ParticipantProgramLite> findByIdUserIdAndIdOrgIdOrderByProgramDesc(Long userId, Long orgId);
}
