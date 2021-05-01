package com.ojas.gcp.firstappenginetryout.entity.internshipTest;

import java.util.List;

public class ProgramTest extends ProgramMeta{
    private String description;
    //no of seats
    private Integer programSize;
    private List<MentorMeta> mentors;

    public ProgramTest() {
        super();
    }

    public ProgramTest(ProgramMeta meta, String description, Integer programSize, List<MentorMeta> mentors) {
        super(meta.getId(), meta.getName(), meta.getCategory(), meta.getShortDescription(), meta.getPhase(),
                meta.getStartDate(), meta.getDuration());
        this.description = description;
        this.programSize = programSize;
        this.mentors = mentors;
    }

    public ProgramTest(Long id, String name, String category, String shortDescription, String description, Phase phase, String startDate,
                   String endDate, Integer programSize, List<MentorMeta> mentors) {

        super(id, name, category, shortDescription, phase, startDate, endDate);
        this.description = description;
        this.programSize = programSize;
        this.mentors = mentors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgramSize() {
        return programSize;
    }

    public void setProgramSize(Integer programSize) {
        this.programSize = programSize;
    }

    public List<MentorMeta> getMentors() {
        return mentors;
    }

    public void setMentors(List<MentorMeta> mentors) {
        this.mentors = mentors;
    }
}

