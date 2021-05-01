package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Student;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends AppUserBaseRepository<Student>{
    Optional<Student> findByEmail(String email);
}