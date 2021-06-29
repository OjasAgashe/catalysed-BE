package com.ojas.gcp.firstappenginetryout.repository.projection;

import com.ojas.gcp.firstappenginetryout.entity.enums.ProgramStatus;
import org.springframework.beans.factory.annotation.Value;

public interface ParticipantProgramLite {
    @Value("#{target.program.id}")
    Long getId();

    @Value("#{target.program.status}")
    ProgramStatus getStatus();

    @Value("#{target.program.title}")
    String getTitle();
}
