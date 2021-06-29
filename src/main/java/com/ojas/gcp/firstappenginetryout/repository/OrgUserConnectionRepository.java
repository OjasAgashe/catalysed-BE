package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.OrgUserConnection;
import com.ojas.gcp.firstappenginetryout.entity.OrgUserPK;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrgUserConnectionRepository extends JpaRepository<OrgUserConnection, OrgUserPK> {
    List<OrgUserConnection> findByIdOrgId(Long orgId);

    Optional<OrgUserConnection> findByIdOrgIdAndIdUserId(Long orgId, Long userId);

    List<OrgUserConnection> findByIdOrgIdAndUserType(Long orgId, UserType type);
}
