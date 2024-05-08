package learn.app_tracker.controllers;

import learn.app_tracker.domain.JobApplicationService;
import learn.app_tracker.domain.Result;
import learn.app_tracker.models.JobApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/job/application")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service){
        this.service = service;
    }

    // find all
    @GetMapping
    public List<JobApplication> findAll() {
        return service.findAll();
    }

    // find by id
    @GetMapping("/{applicationId}")
    public ResponseEntity<JobApplication> findById(@PathVariable int applicationId) {
        JobApplication application = service.findById(applicationId);
        if (application == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(application);
    }

    // add
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody JobApplication application) {
        Result<JobApplication> result = service.add(application);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    // update
    @PutMapping("/{applicationId}")
    public ResponseEntity<Object> update(@PathVariable int applicationId, @RequestBody JobApplication application) {
        if (applicationId != application.getApplicationId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<JobApplication> result = service.update(application);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    // delete by id
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteById(@PathVariable int applicationId) {
        if (service.deleteById(applicationId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
