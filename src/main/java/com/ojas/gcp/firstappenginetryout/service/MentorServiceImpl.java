package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import com.ojas.gcp.firstappenginetryout.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;

    @Autowired
    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public void saveMentor() {
        Mentor mentor = new Mentor();
        mentor.setEmail("ojas@gmail.com");
        mentor.setType(UserType.MENTOR);
        mentor.setPassword("password");
        mentor.setFirstName("ojas");
        mentor.setLastName("Agashe");
        mentor.setLocation("Mumbai");
        mentor.setGender("male");

        mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getMentors() {
        return mentorRepository.findAll();
    }
}
