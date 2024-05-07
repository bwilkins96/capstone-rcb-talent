package learn.app_tracker.data;

import learn.app_tracker.models.JobPosting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(result.contains(getTestPosting()));
    }

    @Test
    void shouldFindById() {
        JobPosting actual = repository.findById(1);
    }

    private JobPosting getTestPosting() {
        JobPosting posting = new JobPosting();

        posting.setPostingId(1);
        posting.setRole("Software Developer");
        posting.setLevel("Entry Level");
        posting.setVisaSponsorship(false);
        posting.setDegree("Bachelors");

        return posting;
    }

}