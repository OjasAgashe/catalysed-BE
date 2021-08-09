package com.ojas.gcp.firstappenginetryout.repository.projection;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import org.springframework.beans.factory.annotation.Value;

public interface ParticipantProgramMeta {
    @Value("#{target.program.id}")
    Long getId();

    @Value("#{target.program.title}")
    String getTitle();

    @Value("#{target.program.tentativeStartDate}")
    String getTentativeStartDate();

    @Value("#{target.program.durationInMonths}")
    int getDurationInMonths();

    @Value("#{target.program.mode}")
    String getMode();

    @Value("#{target.program.languageRequirements}")
    String getLanguageRequirements();

    @Value("#{target.program.status}")
    ProgramStatus getStatus();
}
