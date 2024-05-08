package learn.app_tracker.domain;

import learn.app_tracker.data.InterviewRepository;
import learn.app_tracker.data.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JobApplicationServiceTest {

    @Autowired
    JobApplicationService service;

    @MockBean
    JobApplicationRepository repository;

    @MockBean
    InterviewRepository interviewRepository;

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldNotAddNull() {

    }

    @Test
    void shouldNotAddMissingPostingId() {

    }

    @Test
    void shouldNotAddNullStatus() {

    }

    @Test
    void shouldNotAddNullDateApplied() {

    }

    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldNotUpdateNull() {

    }

    @Test
    void shouldNotUpdateMissingId() {

    }

    @Test
    void shouldNotUpdateMissingPostingId() {

    }

    @Test
    void shouldNotUpdateNullStatus() {

    }

    @Test
    void shouldNotUpdateNullOrigin() {

    }

    @Test
    void shouldNotUpdateNullDateApplied() {

    }

}