package com.ojas.gcp.firstappenginetryout.repository.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ParticipantProgramTitle {
    @Value("#{target.program.title}")
    String getTitle();
}
