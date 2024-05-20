package learn.app_tracker.data;

import learn.app_tracker.models.JobPosting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.getTestPosting;
import static utils.TestUtils.getTestPostingFull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JobPostingJdbcTemplateRepositoryTest {

    @Autowired
    JobPostingJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<JobPosting> result = repository.findAll();

        assertEquals(3, result.size());
        assertEquals(getTestPosting(), result.get(0));
        assertTrue(result.contains(getTestPosting()));
    }

    @Test
    void shouldFindById() {
        JobPosting actual = repository.findById(1);

        assertEquals(getTestPostingFull(), actual);
    }

}