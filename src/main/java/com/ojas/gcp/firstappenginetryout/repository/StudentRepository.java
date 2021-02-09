package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    Student findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Student> findById(Long id);

    Student findByEmail(String emailId);
}