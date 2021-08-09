package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import com.ojas.gcp.firstappenginetryout.repository.projection.ParticipantProgramTitle;
import com.ojas.gcp.firstappenginetryout.repository.projection.ProgramMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByTitle(String title);

    List<ProgramMeta> findByOrganizationIdAndIdNotInAndStatusNot(Long orgId, List<Long> excludingPrograms, ProgramStatus status);
}
