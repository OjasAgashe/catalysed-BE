package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.AppInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInvitationsRepository extends JpaRepository<AppInvitation, Long> {
}
