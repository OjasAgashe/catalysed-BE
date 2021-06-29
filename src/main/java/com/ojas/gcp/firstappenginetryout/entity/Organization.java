package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "org_logo")
    private String orgLogo;
    @Column(name = "website")
    private String website;
    @Column(name = "social_media_code")
    private String socialMediaCode;
    @Column(name = "social_media_link")
    private String socialMediaLink;
    @Column(name = "is_verified")
    private boolean isVerified;
    @Column(name = "subscription")
    private String subscription;
    @OneToOne
    @JoinColumn(name = "org_user_id")
    @JsonBackReference
    private OrganizationUser organizationUser;
    @OneToMany(mappedBy = "organization")
    private List<Program> programs;

    @OneToMany(mappedBy = "organization")
    @JsonManagedReference
    private Set<OrgUserConnection> participants = new HashSet<OrgUserConnection>();


    public Organization() {

    }

    public Organization(String name, String description, String website, String socialMediaCode, String socialMediaLink) {
        this.name = name;
        this.description = description;
        this.website = website;
        this.socialMediaCode = socialMediaCode;
        this.socialMediaLink = socialMediaLink;
    }

    public Organization(String name, String description, String orgLogo, String website, String socialMediaCode,
                        String socialMediaLink, boolean isVerified, String subscription, List<Program> programs) {
        this.name = name;
        this.description = description;
        this.orgLogo = orgLogo;
        this.website = website;
        this.socialMediaCode = socialMediaCode;
        this.socialMediaLink = socialMediaLink;
        this.isVerified = isVerified;
        this.subscription = subscription;
        this.programs = programs;
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

    public boolean isVerified() {
        return isVerified;
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

    public boolean getVerified() {
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

    public OrganizationUser getOrganizationUser() {
        return organizationUser;
    }

    public void setOrganizationUser(OrganizationUser organizationUser) {
        this.organizationUser = organizationUser;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public Set<OrgUserConnection> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<OrgUserConnection> participants) {
        this.participants = participants;
    }
}
