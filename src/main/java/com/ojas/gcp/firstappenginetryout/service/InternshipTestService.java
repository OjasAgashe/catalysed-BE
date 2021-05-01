package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import com.ojas.gcp.firstappenginetryout.entity.internshipTest.MentorMeta;
import com.ojas.gcp.firstappenginetryout.entity.internshipTest.MentorTest;
import com.ojas.gcp.firstappenginetryout.entity.internshipTest.ProgramMeta;
import com.ojas.gcp.firstappenginetryout.entity.internshipTest.ProgramTest;
import com.ojas.gcp.firstappenginetryout.repository.MentorRepository;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.ojas.gcp.firstappenginetryout.entity.internshipTest.Phase.*;

@Component
public class InternshipTestService {
    public static Map<Long, ProgramTest> programMap;
    public static List<ProgramMeta> programMetaList;
    public static Map<Long, MentorTest> mentorMap;
    public static List<MentorMeta> mentorMetaList;

    static {
        programMap = new HashMap<>();
        programMetaList = new ArrayList<>();
        ProgramMeta programMeta;
        programMeta = new ProgramMeta(1l, "Teach for India - Pilot", "School Education", "Pilot program for TFI in Maharashtra", APPLICATION_OPEN, "15-12-2020", "2 months");
        programMetaList.add(programMeta);
        ProgramTest one = new ProgramTest(programMeta, "description", 80, new ArrayList<>());

        programMeta = new ProgramMeta(2l, "Classical Music from the roots", "Music and Arts", "Learn classical music of India", APPLICATION_REVIEW, "15-11-2020", "15 months");
        programMetaList.add(programMeta);
        ProgramTest two = new ProgramTest(programMeta, "description", 15, new ArrayList<>());

        programMeta = new ProgramMeta(3l, "English for beginners", "Language Studies", "English from Alphabet to Sentences", APPLICATION_OPEN, "01-2-2021", "6 months");
        programMetaList.add(programMeta);
        ProgramTest three = new ProgramTest(programMeta, "description", 24, new ArrayList<>());

        programMeta = new ProgramMeta(4l, "Mathematics for middle schoolers", "School Education", "Maths for class 5th through 8th", IN_PROGRESS, "10-01-2021", "12 months");
        programMetaList.add(programMeta);
        ProgramTest four = new ProgramTest(programMeta, "description", 120, new ArrayList<>());

        programMeta = new ProgramMeta(5l, "Environmental Studies", "School Education", "Learn about the different processes of nature", APPLICATION_OPEN, "15-02-2021", "3 months");
        programMetaList.add(programMeta);
        ProgramTest five = new ProgramTest(programMeta, "description", 50, new ArrayList<>());

        programMeta = new ProgramMeta(6l, "Modern Chess openings", "Chess", "Chess for medium to advance players", APPLICATION_REVIEW, "08-01-2021", "6 months");
        programMetaList.add(programMeta);
        ProgramTest six = new ProgramTest(programMeta, "description", 10, new ArrayList<>());

        programMeta = new ProgramMeta(7l, "Personal Hygiene for Kids", "Home Science", "Fun activities and lessons to teach peronal hygiene to kids", COMPLETED, "01-08-2020", "3 months");
        programMetaList.add(programMeta);
        ProgramTest seven = new ProgramTest(programMeta, "description", 60, new ArrayList<>());

        programMap.put(1l, one);
        programMap.put(2l, two);
        programMap.put(3l, three);
        programMap.put(4l, four);
        programMap.put(5l, five);
        programMap.put(6l, six);
        programMap.put(7l, seven);


        mentorMap = new HashMap<>();
        mentorMetaList = new ArrayList<>();
        MentorMeta mentorMeta;

        mentorMeta = new MentorMeta(1l, "Ramesh", "Nigam", "https://storage.googleapis.com/catalysed-mentors/ramesh_nigam.jpg", Arrays.asList("Social Science", "English"), "ramesh.nigam@outlook.com");
        mentorMetaList.add(mentorMeta);
        mentorMap.put(1l, new MentorTest(mentorMeta, "Male", new MentorTest.WorkingHours("10:00am", "06:30pm"), "Mumbai"));

        mentorMeta = new MentorMeta(2l, "Ayushi", "Sharma", "https://storage.googleapis.com/catalysed-mentors/ayushi_sharma.jpg", Arrays.asList("Classical Music", "Jazz", "Instrumental", "Music production", "Theatrical"), "ayushi.sharma@gmail.com");
        mentorMetaList.add(mentorMeta);
        mentorMap.put(2l, new MentorTest(mentorMeta, "Female", new MentorTest.WorkingHours("08:00am", "05:00pm"), "Bangalore"));

        mentorMeta = new MentorMeta(3l, "Tejasvi", "Patil", "https://storage.googleapis.com/catalysed-mentors/tejasvi_patil.jpg", new ArrayList<>(), "tejasvi.patil@gmail.com");
        mentorMetaList.add(mentorMeta);
        mentorMap.put(3l, new MentorTest(mentorMeta, "Female", new MentorTest.WorkingHours("10:00am", "07:00pm"), "Chennai"));

        mentorMeta = new MentorMeta(4l, "Fatima", "Khan", "https://storage.googleapis.com/catalysed-mentors/fatima_khan.jpg", Arrays.asList("Home Science", "Economics"), "fatima.khan@yahoo.co.in");
        mentorMetaList.add(mentorMeta);
        mentorMap.put(4l, new MentorTest(mentorMeta, "Female", new MentorTest.WorkingHours("09:00am", "05:30pm"), "Delhi"));

        mentorMeta = new MentorMeta(5l, "David", "Joseph", "https://storage.googleapis.com/catalysed-mentors/david_joseph.jpg", Arrays.asList("Chess", "Painting", "Sculpting"), "david.joseph@outlook.com");
        mentorMetaList.add(mentorMeta);
        mentorMap.put(5l, new MentorTest(mentorMeta, "Male", new MentorTest.WorkingHours("12:00pm", "07:30pm"), "Chennai"));
    }
    public List<ProgramMeta> getPrograms() {
        return programMetaList;
    }

    public ProgramTest getProgramDetails(Long programId) {
        ProgramTest program =  programMap.get(programId);
        program.setMentors(getMentors());
        return program;
    }

    public List<MentorMeta> getMentors() {
        return mentorMetaList;
    }

    public MentorTest getMentorDetails(Long mentorId) {
        return mentorMap.get(mentorId);
    }
}
