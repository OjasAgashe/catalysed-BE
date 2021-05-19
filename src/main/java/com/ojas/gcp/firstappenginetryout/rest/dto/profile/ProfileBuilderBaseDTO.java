package com.ojas.gcp.firstappenginetryout.rest.dto.profile;

import com.ojas.gcp.firstappenginetryout.rest.dto.ContactDetailsDTO;
import com.ojas.gcp.firstappenginetryout.rest.dto.LocationDTO;

public class ProfileBuilderBaseDTO {
    private ContactDetailsDTO contactDetails;
    private String primaryLanguage;
    private LocationDTO location;

    public ProfileBuilderBaseDTO() {

    }

    public ProfileBuilderBaseDTO(ContactDetailsDTO contactDetails, String primaryLanguage, LocationDTO location) {
        this.contactDetails = contactDetails;
        this.primaryLanguage = primaryLanguage;
        this.location = location;
    }

    public ContactDetailsDTO getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsDTO contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
