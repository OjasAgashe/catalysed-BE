package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "tentative_start_date")
//    private LocalDateTime tentativeStartDate;
    private String tentativeStartDate;
    @Column(name = "duration_in_months")
    private int durationInMonths;
    @Column(name = "mode")
    private String mode;
    @Column(name = "language_requirements")
    private String languageRequirements;
    @Column(name = "student_age_from")
    private int studentAgeLimitFrom;
    @Column(name = "student_age_to")
    private int studentAgeLimitTo;
    @Column(name = "program_link")
    private String programLink;
    @Column(name = "coordinator_name")
    private String coordinatorName;
    @Column(name = "coordinator_email")
    private String coordinatorEmail;
    @Column(name = "coordinator_phone_country_name")
    private String coordinatorPhoneCountryName;
    @Column(name = "coordinator_phone_country_code")
    private String coordinatorPhoneCountryCode;
    @Column(name = "coordinator_phone_number")
    private String coordinatorPhoneNumber;
    @Enumerated(EnumType.STRING)
    private ProgramStatus status;

    @ManyToOne
    @JoinColumn(name="org_id", nullable=false)
    private Organization organization;

    @OneToOne(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ProgramStudentFields studentFields;

    @OneToOne(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ProgramMentorFields mentorFields;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProgramInvitation> invitations;


    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramStatus status) {
        this.status = status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTentativeStartDate() {
        return tentativeStartDate;
    }

    public void setTentativeStartDate(String tentativeStartDate) {
        this.tentativeStartDate = tentativeStartDate;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLanguageRequirements() {
        return languageRequirements;
    }

    public void setLanguageRequirements(String languageRequirements) {
        this.languageRequirements = languageRequirements;
    }

    public int getStudentAgeLimitFrom() {
        return studentAgeLimitFrom;
    }

    public void setStudentAgeLimitFrom(int studentAgeLimitFrom) {
        this.studentAgeLimitFrom = studentAgeLimitFrom;
    }

    public int getStudentAgeLimitTo() {
        return studentAgeLimitTo;
    }

    public void setStudentAgeLimitTo(int studentAgeLimitTo) {
        this.studentAgeLimitTo = studentAgeLimitTo;
    }

    public String getProgramLink() {
        return programLink;
    }

    public void setProgramLink(String programLink) {
        this.programLink = programLink;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getCoordinatorEmail() {
        return coordinatorEmail;
    }

    public void setCoordinatorEmail(String coordinatorEmail) {
        this.coordinatorEmail = coordinatorEmail;
    }

    public String getCoordinatorPhoneCountryName() {
        return coordinatorPhoneCountryName;
    }

    public void setCoordinatorPhoneCountryName(String coordinatorPhoneCountryName) {
        this.coordinatorPhoneCountryName = coordinatorPhoneCountryName;
    }

    public String getCoordinatorPhoneCountryCode() {
        return coordinatorPhoneCountryCode;
    }

    public void setCoordinatorPhoneCountryCode(String coordinatorPhoneExtension) {
        this.coordinatorPhoneCountryCode = coordinatorPhoneExtension;
    }

    public String getCoordinatorPhoneNumber() {
        return coordinatorPhoneNumber;
    }

    public void setCoordinatorPhoneNumber(String coordinatorPhoneNumber) {
        this.coordinatorPhoneNumber = coordinatorPhoneNumber;
    }

    public ProgramStudentFields getStudentFields() {
        return studentFields;
    }

    public void setStudentFields(ProgramStudentFields studentFields) {
        this.studentFields = studentFields;
    }

    public ProgramMentorFields getMentorFields() {
        return mentorFields;
    }

    public void setMentorFields(ProgramMentorFields mentorFields) {
        this.mentorFields = mentorFields;
    }

    public List<ProgramInvitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<ProgramInvitation> invitations) {
        this.invitations = invitations;
    }
}
