package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.PhoneDTO;

public class OrgDetailsProfileDTO {
    private LocationDTO address;
    private String workDescription;
    private PhoneDTO phone;
    private String contactEmail;
    private String description;
    private String name;
    private String primaryLanguage;
    private String website;
    private String socialMediaCode;
    private String socialMediaLink;
    private String yearOfInception;

    public OrgDetailsProfileDTO() {
    }

    public OrgDetailsProfileDTO(LocationDTO address, String workDescription, PhoneDTO phone, String contactEmail,
                                String description, String name, String primaryLanguage, String website,
                                String socialMediaCode, String socialMediaLink, String yearOfInception) {
        this.address = address;
        this.workDescription = workDescription;
        this.phone = phone;
        this.contactEmail = contactEmail;
        this.description = description;
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.website = website;
        this.socialMediaCode = socialMediaCode;
        this.socialMediaLink = socialMediaLink;
        this.yearOfInception = yearOfInception;
    }

    public LocationDTO getAddress() {
        return address;
    }

    public void setAddress(LocationDTO address) {
        this.address = address;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSocialMediaCode() {
        return socialMediaCode;
    }

    public void setSocialMediaCode(String socialMediaCode) {
        this.socialMediaCode = socialMediaCode;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getYearOfInception() {
        return yearOfInception;
    }

    public void setYearOfInception(String yearOfInception) {
        this.yearOfInception = yearOfInception;
    }
}

