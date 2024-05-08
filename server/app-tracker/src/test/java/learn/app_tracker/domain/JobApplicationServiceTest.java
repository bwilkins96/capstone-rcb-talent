package learn.app_tracker.domain;

import learn.app_tracker.data.JobApplicationRepository;
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

}