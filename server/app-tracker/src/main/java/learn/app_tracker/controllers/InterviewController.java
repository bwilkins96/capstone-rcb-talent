package learn.app_tracker.controllers;

import learn.app_tracker.domain.InterviewService;
import learn.app_tracker.domain.Result;
import learn.app_tracker.models.Interview;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService service;

    public InterviewController(InterviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Interview> findAll() {
        return service.findAll();
    }

    @GetMapping("/{interviewId}")
    public Interview findById(@PathVariable int interviewId) {
        return service.findById(interviewId);
    }

    @GetMapping("/appId/{applicationId}")
    public List<Interview> findAllByApplicationId(@PathVariable int applicationId) {
        return service.findAllByApplicationId(applicationId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Interview interview) {
        Result<Interview> result = service.add(interview);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{interviewId}")
    public ResponseEntity<Object> update(@PathVariable int interviewId, @RequestBody Interview interview) {
        if (interviewId != interview.getInterviewId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Interview> result = service.update(interview);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<Void> deleteById(@PathVariable int interviewId) {
        if (service.deleteById(interviewId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
