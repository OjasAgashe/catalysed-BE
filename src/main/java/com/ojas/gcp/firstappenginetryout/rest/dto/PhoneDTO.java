package com.ojas.gcp.firstappenginetryout.rest.dto;

public class PhoneDTO {
    private String countryCode;
    private String countryName;
    private String number;

    public PhoneDTO() {

    }

    public PhoneDTO(String countryName, String countryCode, String number) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
