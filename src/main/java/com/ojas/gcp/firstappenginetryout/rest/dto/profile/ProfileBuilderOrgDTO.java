package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.rest.dto.ContactDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;

public class ProfileBuilderOrgDTO extends ProfileBuilderBaseDTO{
    private String workDescription;
    private String yearOfInception;

    public ProfileBuilderOrgDTO() {

    }

    public ProfileBuilderOrgDTO(ContactDetailsDTO contactDetails, LocationDTO location,
                                String primaryLanguage, String workDescription, String yearOfInception) {
        super(contactDetails, primaryLanguage, location);
        this.workDescription = workDescription;
        this.yearOfInception = yearOfInception;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getYearOfInception() {
        return yearOfInception;
    }

    public void setYearOfInception(String yearOfInception) {
        this.yearOfInception = yearOfInception;
    }
}
