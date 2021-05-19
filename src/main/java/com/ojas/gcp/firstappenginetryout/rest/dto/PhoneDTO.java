package com.ojas.gcp.firstappenginetryout.rest.dto;

public class PhoneDTO {
    private String countryCode;
    private String number;

    public PhoneDTO() {

    }

    public PhoneDTO(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
