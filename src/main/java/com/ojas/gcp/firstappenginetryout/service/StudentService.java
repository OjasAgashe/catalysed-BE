package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.rest.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    public List<StudentDTO> getStudentList();
//
//    public StudentDTO getStudent(Long studentId);
//
    public StudentDTO createStudent(StudentDTO studentDTO);
//
//    public boolean removeStudent(StudentDTO studentDTO);
//
    public StudentDTO updateStudent(StudentDTO studentDTO);
}
