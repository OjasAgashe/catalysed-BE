package com.ojas.gcp.firstappenginetryout.rest.dto;

public class OrgMetaDTO {
    private Long id;
    private String name;
    private String description;
    private String orgLogo;
    private String website;
    private String socialMediaCode;
    private String socialMediaLink;

    public OrgMetaDTO() {

    }

    public OrgMetaDTO(Long id, String name, String description, String orgLogo, String website,
                      String socialMediaCode, String socialMediaLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.orgLogo = orgLogo;
        this.website = website;
        this.socialMediaCode = socialMediaCode;
        this.socialMediaLink = socialMediaLink;
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
}
