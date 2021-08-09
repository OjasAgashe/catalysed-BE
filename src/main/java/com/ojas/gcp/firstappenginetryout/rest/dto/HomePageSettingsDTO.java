package com.ojas.gcp.firstappenginetryout.rest.dto;

public class HomePageSettingsDTO {
    private boolean accountVerified;
    private boolean profileCreated;

    public HomePageSettingsDTO() {

    }

    public HomePageSettingsDTO(boolean accountVerified, boolean profileCreated) {
        this.accountVerified = accountVerified;
        this.profileCreated = profileCreated;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public boolean isProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(boolean profileCreated) {
        this.profileCreated = profileCreated;
    }
}
