package com.ojas.gcp.firstappenginetryout.service;

import com.ojas.gcp.firstappenginetryout.entity.Mentor;
import java.util.List;

public interface MentorService {
    public void saveMentor();

    public List<Mentor> getMentors();
}
