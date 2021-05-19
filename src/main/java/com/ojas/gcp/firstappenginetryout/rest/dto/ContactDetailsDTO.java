package com.ojas.gcp.firstappenginetryout.rest.dto;

public class ContactDetailsDTO {
    private String email;
    private PhoneDTO phone;

    public ContactDetailsDTO() {

    }

    public ContactDetailsDTO(String email, PhoneDTO phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }
}
