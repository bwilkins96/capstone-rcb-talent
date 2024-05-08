package learn.app_tracker.domain;

import learn.app_tracker.data.JobApplicationRepository;
import learn.app_tracker.models.JobApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    public JobApplication findById(int applicationId) {
        return repository.findById(applicationId);
    }

    public Result<JobApplication> add(JobApplication application) {
        return null;
    }

    public Result<JobApplication> update(JobApplication application) {
        // application id is required and must be greater than 0

        return null;
    }

    public boolean deleteById(int applicationId) {
        return repository.deleteById(applicationId);
    }

    private Result<JobApplication> validate() {
        // application cannot be null
        // posting id is required
        // status cannot be null,
        // origin cannot be null,
        // date applied cannot be null

        return null;
    }

}
