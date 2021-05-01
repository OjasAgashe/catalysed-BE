package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByTitle(String title);
}
