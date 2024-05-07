package learn.app_tracker.data;

import learn.app_tracker.models.JobApplication;

import java.util.List;

public interface JobApplicationRepository {

    List<JobApplication> findAll();

    JobApplication findById(int applicationId);

    JobApplication add(JobApplication application);

    boolean update(JobApplication application);

    boolean deleteById(int applicationId);

}
