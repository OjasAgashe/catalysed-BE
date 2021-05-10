package com.ojas.gcp.firstappenginetryout.rest.dto;

public class OrganizationDetailsDTO {
    private Long id;
    private String name;
    private String description;
    private String orgLogo;
    private String website;
    private String socialMediaCode;
    private String socialMediaLink;
    private boolean isVerified;
    private String subscription;

    public OrganizationDetailsDTO() {

    }

    public OrganizationDetailsDTO(Long id, String name, String description, String orgLogo, String website,
                                  String socialMediaLink, String socialMediaCode, boolean isVerified, String subscription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.orgLogo = orgLogo;
        this.website = website;
        this.socialMediaLink = socialMediaLink;
        this.socialMediaCode = socialMediaCode;
        this.isVerified = isVerified;
        this.subscription = subscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getSocialMediaCode() {
        return socialMediaCode;
    }

    public void setSocialMediaCode(String socialMediaCode) {
        this.socialMediaCode = socialMediaCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
