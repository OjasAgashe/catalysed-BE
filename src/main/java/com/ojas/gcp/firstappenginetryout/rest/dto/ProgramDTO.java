package com.ojas.gcp.firstappenginetryout.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;

public class ProgramDTO {
    private Long id;
    private String title;
    private String description;
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
    private String tentativeStartDate;
    private int durationInMonths;
    private String mode;
    private String languageRequirements;
    private AgeLimit ageLimit;
    private String programLink;
    private Coordinator coordinator;
    private StudentFields studentFields;
    private MentorFields mentorFields;
    private ProgramStatus status;

    public ProgramDTO() {
    }

    public ProgramDTO(Long id, String title, String description, String tentativeStartDate, int durationInMonths,
                      String mode, String languageRequirements, AgeLimit ageLimit, String programLink,
                      Coordinator coordinator, StudentFields studentFields, MentorFields mentorFields, ProgramStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tentativeStartDate = tentativeStartDate;
        this.durationInMonths = durationInMonths;
        this.mode = mode;
        this.languageRequirements = languageRequirements;
        this.ageLimit = ageLimit;
        this.programLink = programLink;
        this.coordinator = coordinator;
        this.studentFields = studentFields;
        this.mentorFields = mentorFields;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AgeLimit getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(AgeLimit ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getProgramLink() {
        return programLink;
    }

    public void setProgramLink(String programLink) {
        this.programLink = programLink;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public StudentFields getStudentFields() {
        return studentFields;
    }

    public void setStudentFields(StudentFields studentFields) {
        this.studentFields = studentFields;
    }

    public MentorFields getMentorFields() {
        return mentorFields;
    }

    public void setMentorFields(MentorFields mentorFields) {
        this.mentorFields = mentorFields;
    }

    public ProgramStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramStatus status) {
        this.status = status;
    }

    public static class AgeLimit {
        private int from;
        private int to;

        public AgeLimit() {
        }

        public AgeLimit(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }

    public static class Coordinator {
        private String name;
        private String email;
        private PhoneDTO contact;

        public Coordinator() {
        }

        public Coordinator(String name, String email, PhoneDTO contact) {
            this.name = name;
            this.email = email;
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public PhoneDTO getContact() {
            return contact;
        }

        public void setContact(PhoneDTO contact) {
            this.contact = contact;
        }
    }

    public static class StudentFields {
        private String subjectRequirements;
        private int openings;
//        @JsonDeserialize(using = LocalDateDeserializer.class)
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
        private String applyBy;
        //application end date
        @JsonProperty("isPaid")
        private boolean paid;
        private double programFees;
        private String generalInstructions;

        public StudentFields() {
        }

        public StudentFields(String subjectRequirements, int openings, boolean paid, String applyBy,
                             double programFees, String generalInstructions) {
            this.subjectRequirements = subjectRequirements;
            this.openings = openings;
            this.applyBy = applyBy;
            this.paid = paid;
            this.programFees = programFees;
            this.generalInstructions = generalInstructions;
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

        public boolean getPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
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
    }

    public static class MentorFields {
        private String subjectRequirements;
        private int openings;
//        @JsonDeserialize(using = LocalDateDeserializer.class)
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
        private String applyBy;
        private String generalInstructions;

        public MentorFields() {
        }

        public MentorFields(String subjectRequirements, int openings, String applyBy, String generalInstructions) {
            this.subjectRequirements = subjectRequirements;
            this.openings = openings;
            this.applyBy = applyBy;
            this.generalInstructions = generalInstructions;
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
    }
}
