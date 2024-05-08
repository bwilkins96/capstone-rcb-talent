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
    void shouldNotUpdateInvalid() {

    }

    @Test
    void shouldUpdateValid() {

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
        interview.setApplicationId(1);
        interview.setType(InterviewType.TECHNICAL);
        interview.setResult(InterviewResult.FAIL);
        interview.setWhen(LocalDateTime.of(2024, 3, 1, 12, 15, 0));
        interview.setNotes("This is a note.");
        return interview;
    }
}