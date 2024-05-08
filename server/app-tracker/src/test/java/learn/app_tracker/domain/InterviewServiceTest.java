package learn.app_tracker.domain;

import learn.app_tracker.data.DataException;
import learn.app_tracker.data.InterviewRepository;
import learn.app_tracker.data.JobApplicationRepository;
import learn.app_tracker.models.Interview;
import learn.app_tracker.models.JobApplication;
import learn.app_tracker.models.enums.InterviewResult;
import learn.app_tracker.models.enums.InterviewType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InterviewServiceTest {

    @Autowired
    InterviewService service;

    @MockBean
    InterviewRepository repository;

    @MockBean
    JobApplicationRepository jobApplicationRepository;

    @Test
    void shouldNotAddInvalid() throws DataException {
        // Null interview
        Result<Interview> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Application ID is not provided
        Interview interview = makeInterview();
        interview.setInterviewId(0);
        interview.setApplicationId(0);
        result = service.add(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Application does not exist
        interview.setApplicationId(1);
        when(jobApplicationRepository.findById(1)).thenReturn(null);
        result = service.add(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview type is null
        JobApplication app = new JobApplication();
        when(jobApplicationRepository.findById(1)).thenReturn(app);
        interview.setType(null);
        result = service.add(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview Result is null
        interview.setType(InterviewType.TECHNICAL);
        interview.setResult(null);
        result = service.add(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview ID is set
        interview.setResult(InterviewResult.FAIL);
        interview.setInterviewId(1);
        result = service.add(interview);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldAddWhenValid() throws DataException {
        JobApplication application = new JobApplication();
        when(jobApplicationRepository.findById(1)).thenReturn(application);

        Interview expected = makeInterview();
        Interview actual = makeInterview();
        actual.setInterviewId(0);

        when(repository.add(actual)).thenReturn(expected);
        Result<Interview> result = service.add(actual);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotUpdateInvalid() throws DataException {
        // Null interview
        Result<Interview> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Application ID is not provided
        Interview interview = makeInterview();
        interview.setApplicationId(0);
        result = service.update(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Application does not exist
        interview.setApplicationId(1);
        when(jobApplicationRepository.findById(1)).thenReturn(null);
        result = service.update(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview type is null
        JobApplication app = new JobApplication();
        when(jobApplicationRepository.findById(1)).thenReturn(app);
        interview.setType(null);
        result = service.update(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview Result is null
        interview.setType(InterviewType.TECHNICAL);
        interview.setResult(null);
        result = service.update(interview);
        assertEquals(ResultType.INVALID, result.getType());

        // Interview ID is not set
        interview.setResult(InterviewResult.FAIL);
        interview.setInterviewId(0);
        result = service.update(interview);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdateValid() throws DataException {
        JobApplication application = new JobApplication();
        when(jobApplicationRepository.findById(1)).thenReturn(application);

        Interview interview = makeInterview();
        interview.setResult(InterviewResult.PASS);

        when(repository.update(interview)).thenReturn(true);
        Result<Interview> result = service.update(interview);

        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteWhenNotFound() throws DataException {
        when(repository.deleteById(40)).thenReturn(false);
        assertFalse(service.deleteById(40));
    }

    @Test
    void shouldDelete() throws DataException {
        when(repository.deleteById(1)).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

    private Interview makeInterview() {
        Interview interview = new Interview();
        interview.setInterviewId(1);
        interview.setApplicationId(1);
        interview.setType(InterviewType.TECHNICAL);
        interview.setResult(InterviewResult.FAIL);
        interview.setWhen(LocalDateTime.of(2024, 3, 1, 12, 15, 0));
        interview.setNotes("This is a note.");
        return interview;
    }
}