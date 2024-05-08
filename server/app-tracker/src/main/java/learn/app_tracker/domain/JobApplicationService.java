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
        Result<JobApplication> result = validate(application);

        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    public Result<JobApplication> update(JobApplication application) {
        Result<JobApplication> result = validateUpdate(application);

        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    public boolean deleteById(int applicationId) {
        return repository.deleteById(applicationId);
    }

    private Result<JobApplication> validateUpdate(JobApplication application) {
        Result<JobApplication> result = validate(application);

        if (!result.isSuccess()) {
            return result;
        }

        // application id is required and must be greater than 0
        if (application.getApplicationId() <= 0) {
            result.addMessage("application id is required and must be greater than 0", ResultType.INVALID);
        }

        return result;
    }

    private Result<JobApplication> validate(JobApplication application) {
        // application cannot be null
        // posting id is required and must be greater than 0
        // status cannot be null,
        // origin cannot be null,
        // date applied cannot be null

        Result<JobApplication> result = new Result<>();

        if (application == null) {
            result.addMessage("application cannot be null", ResultType.INVALID);
            return result;
        }

        if (application.getPosting() == null || application.getPosting().getPostingId() <= 0) {
            result.addMessage("posting id is required and must be greater than 0", ResultType.INVALID);
        }

        if (application.getStatus() == null) {
            result.addMessage("status cannot be null", ResultType.INVALID);
        }

        if (application.getOrigin() == null) {
            result.addMessage("origin cannot be null", ResultType.INVALID);
        }

        if (application.getDateApplied() == null) {
            result.addMessage("date applied cannot be null", ResultType.INVALID);
        }

        return result;
    }

}
