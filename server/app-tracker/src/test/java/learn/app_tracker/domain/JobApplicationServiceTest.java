package learn.app_tracker.domain;

import learn.app_tracker.data.InterviewRepository;
import learn.app_tracker.data.JobApplicationRepository;
import learn.app_tracker.models.JobApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static utils.TestUtils.getTestApplication;

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
        JobApplication toAdd = getTestApplication();
        JobApplication expected = getTestApplication();
        expected.setApplicationId(5);

        when(repository.add(toAdd)).thenReturn(expected);

        Result<JobApplication> result = service.add(toAdd);
        assertTrue(result.isSuccess());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddNull() {
        Result<JobApplication> result = service.add(null);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddMissingPostingId() {
        JobApplication application = getTestApplication();
        application.getPosting().setPostingId(-5);
        Result<JobApplication> result = service.add(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());

        application.setPosting(null);
        result = service.add(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddNullStatus() {
        JobApplication application = getTestApplication();
        application.setStatus(null);
        Result<JobApplication> result = service.add(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAllowNullOrigin() {
        JobApplication application = getTestApplication();
        application.setOrigin(null);
        Result<JobApplication> result = service.add(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddNullDateApplied() {
        JobApplication application = getTestApplication();
        application.setDateApplied(null);
        Result<JobApplication> result = service.add(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        JobApplication toUpdate = getTestApplication();
        toUpdate.setNotes("Updated!");

        when(repository.update(toUpdate)).thenReturn(true);

        Result<JobApplication> result = service.update(toUpdate);
        assertTrue(result.isSuccess());
        assertEquals(toUpdate, result.getPayload());
    }

    @Test
    void shouldNotUpdateNull() {
        Result<JobApplication> result = service.update(null);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateMissingId() {
        JobApplication application = getTestApplication();
        application.setApplicationId(-5);
        Result<JobApplication> result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateMissingPostingId() {
        JobApplication application = getTestApplication();
        application.getPosting().setPostingId(-5);
        Result<JobApplication> result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());

        application.setPosting(null);
        result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateNullStatus() {
        JobApplication application = getTestApplication();
        application.setStatus(null);
        Result<JobApplication> result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateNullOrigin() {
        JobApplication application = getTestApplication();
        application.setOrigin(null);
        Result<JobApplication> result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateNullDateApplied() {
        JobApplication application = getTestApplication();
        application.setDateApplied(null);
        Result<JobApplication> result = service.update(application);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

}