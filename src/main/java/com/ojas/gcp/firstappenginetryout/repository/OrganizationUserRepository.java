package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {
    Optional<OrganizationUser> findById(Long id);
}
