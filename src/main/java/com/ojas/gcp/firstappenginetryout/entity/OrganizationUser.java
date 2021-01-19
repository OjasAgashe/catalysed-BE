package com.ojas.gcp.firstappenginetryout.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_users")
public class OrganizationUser extends AppUser{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "organizationUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Organization organization;

    public OrganizationUser() {

    }

    public OrganizationUser(Long id, Organization organization, String phoneNumber) {
        this.id = id;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
    }

//    public OrganizationUser(String email, String password, String firstName, String lastName, UserType type, Long id, Organization organization, String phoneNumber) {
//        super(email, password, firstName, lastName, type);
//        this.id = id;
//        this.organization = organization;
//        this.phoneNumber = phoneNumber;
//    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
