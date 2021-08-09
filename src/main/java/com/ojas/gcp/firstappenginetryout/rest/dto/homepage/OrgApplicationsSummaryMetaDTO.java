package com.ojas.gcp.firstappenginetryout.rest.dto.homepage;

public class OrgApplicationsSummaryMetaDTO {
    private SummaryDetails mentorDetails;
    private SummaryDetails studentDetails;

    public OrgApplicationsSummaryMetaDTO() {

    }

    public OrgApplicationsSummaryMetaDTO(SummaryDetails mentorDetails, SummaryDetails studentDetails) {
        this.mentorDetails = mentorDetails;
        this.studentDetails = studentDetails;
    }

    public SummaryDetails getMentorDetails() {
        return mentorDetails;
    }

    public void setMentorDetails(SummaryDetails mentorDetails) {
        this.mentorDetails = mentorDetails;
    }

    public SummaryDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(SummaryDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    public static class SummaryDetails {
        private int pending;
        private int notViewed;

        public SummaryDetails() {

        }

        public SummaryDetails(int pending, int notViewed) {
            this.pending = pending;
            this.notViewed = notViewed;
        }

        public int getPending() {
            return pending;
        }

        public void setPending(int pending) {
            this.pending = pending;
        }

        public int getNotViewed() {
            return notViewed;
        }

        public void setNotViewed(int notViewed) {
            this.notViewed = notViewed;
        }
    }
}
