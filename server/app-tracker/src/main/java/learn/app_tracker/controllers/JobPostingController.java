package learn.app_tracker.controllers;

import learn.app_tracker.domain.JobPostingService;
import learn.app_tracker.models.JobPosting;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/job-posting")
public class JobPostingController {

    private final JobPostingService service;

    public JobPostingController(JobPostingService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobPosting> findAll() {
        return service.findAll();
    }

    @GetMapping("/{postingId}")
    JobPosting findById(@PathVariable int postingId) {
        return service.findById(postingId);
    }

}
