package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AppUserBaseRepository<T extends AppUser> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
    Optional<T> findById(Long id);
}
