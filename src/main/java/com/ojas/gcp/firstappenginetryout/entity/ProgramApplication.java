package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramApplicationStatus;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "program_applications")
public class ProgramApplication {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    @JsonBackReference
    private Program program;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser applicant;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProgramApplicationStatus status;
    @Column(name = "org_id")
    private Long orgId;
    @Column(name = "is_reapplied")
    private boolean reapplied;
    @Column(name = "applied_on")
    private String appliedOn;

    @Column(name = "is_fetched_by_org")
    private boolean fetchedByOrg;
    @Column(name = "is_viewed_by_org")
    private boolean viewedByOrg;

    @Column(name = "question_1")
    private String question1;
    @Column(name = "answer_1")
    private String answer1;
    @Column(name = "question_2")
    private String question2;
    @Column(name = "answer_2")
    private String answer2;
    @Column(name = "question_3")
    private String question3;
    @Column(name = "answer_3")
    private String answer3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public AppUser getApplicant() {
        return applicant;
    }

    public void setApplicant(AppUser applicant) {
        this.applicant = applicant;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ProgramApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramApplicationStatus status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public boolean isReapplied() {
        return reapplied;
    }

    public void setReapplied(boolean reapplied) {
        this.reapplied = reapplied;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public boolean isFetchedByOrg() {
        return fetchedByOrg;
    }

    public void setFetchedByOrg(boolean fetchedByOrg) {
        this.fetchedByOrg = fetchedByOrg;
    }

    public boolean isViewedByOrg() {
        return viewedByOrg;
    }

    public void setViewedByOrg(boolean viewedByOrg) {
        this.viewedByOrg = viewedByOrg;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
}
