package learn.app_tracker.domain;

import learn.app_tracker.data.DataException;
import learn.app_tracker.data.InterviewRepository;
import learn.app_tracker.data.JobApplicationRepository;
import learn.app_tracker.models.Interview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository repository;
    private final JobApplicationRepository jobApplicationRepository;

    public InterviewService(InterviewRepository repository, JobApplicationRepository jobApplicationRepository) {
        this.repository = repository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<Interview> findAll() throws DataException {
        return repository.findAll();
    }

    public List<Interview> findAllByApplicationId(int appId) throws DataException {
        return repository.findAllByApplicationId(appId);
    }
    public Interview findById(int id) throws DataException {
        return repository.findById(id);
    }

    public Result<Interview> add(Interview interview) throws DataException {
        Result<Interview> result = validate(interview);
        if (!result.isSuccess()) {
            return result;
        }

        if (interview.getInterviewId() != 0) {
            result.addMessage("interviewId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        interview = repository.add(interview);
        result.setPayload(interview);
        return result;
    }

    public Result<Interview> update(Interview interview) throws DataException {
        Result<Interview> result = validate(interview);
        if (!result.isSuccess()) {
            return result;
        }

        if (interview.getInterviewId() == 0) {
            result.addMessage("interviewId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(interview)) {
            String msg = String.format("interviewId: %s, not found", interview.getInterviewId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int id) throws DataException {
        return repository.deleteById(id);
    }

    private Result<Interview> validate(Interview interview) {
        Result<Interview> result = new Result<>();
        if (interview == null) {
            result.addMessage("interview cannot be null", ResultType.INVALID);
            return result;
        }

        if (interview.getApplicationId() <= 0) {
            result.addMessage("applicationId is required", ResultType.INVALID);
        } else if (jobApplicationRepository.findById(interview.getApplicationId()) == null) {
            result.addMessage("application must exist", ResultType.INVALID);
        }

        if (interview.getType() == null) {
            result.addMessage("interview type is required", ResultType.INVALID);
        }

        if (interview.getResult() == null) {
            result.addMessage("interview result is required", ResultType.INVALID);
        }

        return result;
    }
}
