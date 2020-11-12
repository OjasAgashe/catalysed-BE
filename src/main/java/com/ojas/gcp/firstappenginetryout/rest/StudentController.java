package com.ojas.gcp.firstappenginetryout.rest;

import com.ojas.gcp.firstappenginetryout.rest.dto.StudentDTO;
import com.ojas.gcp.firstappenginetryout.service.StudentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentController {
    private StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> displayHelloMessage() {
        List<StudentDTO> studentList = studentService.getStudentList();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Object> displayHelloMessage(@PathVariable Long studentId) {
        StudentDTO student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }
}
