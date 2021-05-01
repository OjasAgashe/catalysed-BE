package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Mentor;

import java.util.Optional;

public interface MentorRepository extends AppUserBaseRepository<Mentor>{
    Optional<Mentor> findByEmail(String email);
}
