package com.ojas.gcp.firstappenginetryout.repository.projection;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import org.springframework.beans.factory.annotation.Value;

public interface ProgramMeta {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.tentativeStartDate}")
    String getTentativeStartDate();

    @Value("#{target.durationInMonths}")
    int getDurationInMonths();

    @Value("#{target.mode}")
    String getMode();

    @Value("#{target.languageRequirements}")
    String getLanguageRequirements();

    @Value("#{target.status}")
    ProgramStatus getStatus();
}
