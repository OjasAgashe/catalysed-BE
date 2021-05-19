package com.ojas.gcp.firstappenginetryout.rest.dto.registration;

import com.ojas.gcp.firstappenginetryout.rest.dto.SocialMediaDTO;

public class RegistrationOrgDetailsDTO {

    private String name;
    private String description;
    private String orgWebsite;
    private SocialMediaDTO socialMedia;

    public RegistrationOrgDetailsDTO() {

    }

    public RegistrationOrgDetailsDTO(String name, String description, String orgWebsite, SocialMediaDTO socialMedia) {
        this.name = name;
        this.description = description;
        this.orgWebsite = orgWebsite;
        this.socialMedia = socialMedia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public SocialMediaDTO getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaDTO socialMedia) {
        this.socialMedia = socialMedia;
    }
}
