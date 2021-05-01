package com.ojas.gcp.firstappenginetryout.entity.internshipTest;

public class MentorTest extends MentorMeta{
    public MentorTest(){
        super();
    }

    public MentorTest(MentorMeta mentorMeta, String gender, WorkingHours workingHours, String location) {
        super(mentorMeta.getId(), mentorMeta.getFirstName(), mentorMeta.getLastName(),
                mentorMeta.getProfilePic(), mentorMeta.getSpecializations(), mentorMeta.getEmail());
        this.gender = gender;
        this.workingHours = workingHours;
        this.location = location;
    }

    public String gender;
    public WorkingHours workingHours;
    public String location;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class WorkingHours {
        private String startTime;
        private String endTime;

        public WorkingHours() {
        }

        public WorkingHours(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}

