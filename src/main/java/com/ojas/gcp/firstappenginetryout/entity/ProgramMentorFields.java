package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "program_mentor_fields")
public class ProgramMentorFields {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject_requirements")
    private String subjectRequirements;
    @Column(name = "openings")
    private int openings;
    @Column(name = "apply_by")
    private String applyBy;
    @Column(name = "instructions")
    private String generalInstructions;

    @OneToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private Program program;

    public ProgramMentorFields() {
    }

    public ProgramMentorFields(String subjectRequirements, int openings, String applyBy, String generalInstructions,
                               Program program) {
        this.subjectRequirements = subjectRequirements;
        this.openings = openings;
        this.applyBy = applyBy;
        this.generalInstructions = generalInstructions;
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public String getSubjectRequirements() {
        return subjectRequirements;
    }

    public void setSubjectRequirements(String subjectRequirements) {
        this.subjectRequirements = subjectRequirements;
    }

    public int getOpenings() {
        return openings;
    }

    public void setOpenings(int openings) {
        this.openings = openings;
    }

    public String getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(String applyBy) {
        this.applyBy = applyBy;
    }

    public String getGeneralInstructions() {
        return generalInstructions;
    }

    public void setGeneralInstructions(String generalInstructions) {
        this.generalInstructions = generalInstructions;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
