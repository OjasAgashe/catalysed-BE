package com.ojas.gcp.firstappenginetryout.entity.internshipTest;

import java.util.List;

public class MentorMeta {
    public Long id;
    public String firstName;
    public String lastName;
    public String profilePic;
    public List<String> specializations;
    public String email;

    public MentorMeta() {}

    public MentorMeta(Long id, String firstName, String lastName, String profilePic,
                      List<String> specializations, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.specializations = specializations;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
