package learn.app_tracker.data;

import learn.app_tracker.models.Interview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InterviewJdbcTemplateRepositoryTest {

    @Autowired
    InterviewJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() throws DataException {
        List<Interview> interviews = repository.findAll();
        assertNotNull(interviews);

        assertTrue(interviews.size() >= 3 && interviews.size() <= 5);
    }

    @Test
    void shouldFindAllByApplicationId() throws DataException {
        List<Interview> interviews = repository.findAllByApplicationId(2);
        assertNotNull(interviews);

        assertEquals(1, interviews.size());
    }

    @Test
    void shouldNotFindAnyOfMissingApplication() throws  DataException {
        List<Interview> interviews = repository.findAllByApplicationId(55);
        assertEquals(0, interviews.size());
    }

    @Test
    void shouldFindByExistingId() throws DataException {
        Interview interview = repository.findById(2);
        assertNotNull(interview);
    }

    @Test
    void shouldNotFindMissingID() throws DataException {
        Interview interview = repository.findById(24);
        assertNull(interview);
    }

    @Test
    void shouldAddInterview() throws DataException {

    }

    @Test
    void shouldUpdateExistingInterview() throws DataException {

    }

    @Test
    void shouldNotUpdateMissingInterview() throws DataException {

    }

    @Test
    void shouldDeleteExistingInterview() throws DataException {

    }

    @Test
    void shouldNotDeleteMissingInterview() throws DataException {

    }
}