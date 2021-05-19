package com.ojas.gcp.firstappenginetryout.rest.dto;

public class LocationDTO {
    private String country;
    private String region;

    public LocationDTO() {

    }

    public LocationDTO(String country, String region) {
        this.country = country;
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
