package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Program;
import com.ojas.gcp.firstappenginetryout.entity.ProgramInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramInvitationsRepository extends JpaRepository<ProgramInvitation, Long> {
    List<ProgramInvitation> findByProgramId(Long programId);

    Optional<ProgramInvitation> findByProgramIdAndEmailId(Long programId, String emailId);

    Optional<ProgramInvitation> findByProgramIdAndRecipient(Long programId, Long recipientId);

}
