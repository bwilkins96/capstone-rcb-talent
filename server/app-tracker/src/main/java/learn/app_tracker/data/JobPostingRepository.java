package learn.app_tracker.data;

import learn.app_tracker.models.JobPosting;

import java.util.List;

public interface JobPostingRepository {

    List<JobPosting> findAll();

}
