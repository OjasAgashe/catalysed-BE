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
@Table(name = "program_student_fields")
public class ProgramStudentFields {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject_requirements")
    private String subjectRequirements;
    @Column(name = "openings")
    private int openings;
    @Column(name = "apply_by")
    private String applyBy;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "program_fees")
    private double programFees;
    @Column(name = "instructions")
    private String generalInstructions;

    @OneToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private Program program;

    public ProgramStudentFields() {
    }

    public ProgramStudentFields(String subjectRequirements, int openings, String applyBy, boolean isPaid,
                                double programFees, String generalInstructions, Program program) {
        this.subjectRequirements = subjectRequirements;
        this.openings = openings;
        this.applyBy = applyBy;
        this.isPaid = isPaid;
        this.programFees = programFees;
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getProgramFees() {
        return programFees;
    }

    public void setProgramFees(double programFees) {
        this.programFees = programFees;
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
