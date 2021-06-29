package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.auth.SessionUser;
import com.ojas.gcp.firstappenginetryout.rest.dto.HomePageSettingsDTO;

public interface HomePageService {
    public HomePageSettingsDTO getHomePageSettings(SessionUser user);
}
