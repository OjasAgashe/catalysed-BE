package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.AppInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppInvitationsRepository extends JpaRepository<AppInvitation, Long> {
    Optional<AppInvitation> findByInvitationKey(String inviteKey);

    Optional<AppInvitation> findByEmail(String email);
}
