package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.Student;
import com.ojas.gcp.firstappenginetryout.repository.StudentRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.StudentDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getStudentList() {
        List<Student> studentList = studentRepository.getStudentList();
        return studentList.stream().map(this::getStudentDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudent(Long studentId) {
        return getStudentDTO(studentRepository.getStudent(studentId));
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.saveStudent(getStudent(studentDTO));
        return getStudentDTO(student);
    }

    @Override
    public boolean removeStudent(StudentDTO studentDTO) {
        return true;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        return new StudentDTO();
    }

    private StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO(student.getFirstName(), student.getLastName(),
                student.getGrade(), student.getSchool());
        studentDTO.setMemberType(student.getMemberType());
        studentDTO.setMentors(student.getMentors());
        return studentDTO;
    }

    private Student getStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getFirstName(), studentDTO.getLastName(),
                studentDTO.getGrade(), studentDTO.getSchool());
        student.setMemberType(studentDTO.getMemberType());
        student.setMentors(studentDTO.getMentors());
        return student;
    }
}
