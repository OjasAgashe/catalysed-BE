package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrganizationUserRepository extends AppUserBaseRepository<OrganizationUser>{
//    Optional<OrganizationUser> findById(Long id);

    Optional<OrganizationUser> findByEmail(String email);
}
