package com.ojas.gcp.firstappenginetryout.entity;

import javax.persistence.*;

@Entity
@Table(name = "org_profile")
public class ProfileOrgEO {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Organization organization;
    @Column(name = "location_country")
    private String locationCountry;
    @Column(name = "location_region")
    private String locationRegion;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "contact_phone_country")
    private String contactPhoneCountry;
    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;
    @Column(name = "primary_language")
    private String primaryLanguage;
    @Column(name = "inception_year")
    private String yearOfInception;
    @Column(name = "work_description")
    private String workDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneCountry() {
        return contactPhoneCountry;
    }

    public void setContactPhoneCountry(String contactPhoneCountry) {
        this.contactPhoneCountry = contactPhoneCountry;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getYearOfInception() {
        return yearOfInception;
    }

    public void setYearOfInception(String yearOfInception) {
        this.yearOfInception = yearOfInception;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }
}
