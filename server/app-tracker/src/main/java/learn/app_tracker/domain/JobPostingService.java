package learn.app_tracker.domain;

import learn.app_tracker.data.JobPostingRepository;
import learn.app_tracker.models.JobPosting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingService {

    private final JobPostingRepository repository;

    public JobPostingService(JobPostingRepository repository) {
        this.repository = repository;
    }

    public List<JobPosting> findAll() {
        return repository.findAll();
    }

    public JobPosting findById(int postingId) {
        return repository.findById(postingId);
    }

}
