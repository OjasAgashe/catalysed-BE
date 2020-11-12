package com.ojas.gcp.firstappenginetryout.repository;

import com.ojas.gcp.firstappenginetryout.entity.Grade;
import com.ojas.gcp.firstappenginetryout.entity.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Ojas", "Agashe", Grade.PRE, "Visakha Valley School"));
        studentList.add(new Student("Aditya", "Krishn", Grade.PRE, "Visakha Valley School"));
        studentList.add(new Student("Mannivanan", "Vignesh", Grade.SECONDARY, "Youtube Channel"));
        return studentList;
    }

    public Student getStudent(Long studentId) {
        Student student = new Student("Ojas", "Agashe", Grade.PRE, "Visakha Valley School");
        student.setMentors(Arrays.asList("Jon Simons", "Lakshmi Ramnan", "Usha Katkar"));
        student.setMemberType("Pro Subscription");
        return student;
    }

    public Student saveStudent(Student student) {
        return student;
    }
}
