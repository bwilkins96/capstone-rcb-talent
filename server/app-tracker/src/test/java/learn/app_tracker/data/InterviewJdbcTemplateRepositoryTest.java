package learn.app_tracker.data;

import learn.app_tracker.models.Interview;
import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.InterviewResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InterviewJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 5;

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

        assertTrue(interviews.size() >= 3 && interviews.size() <= 6);
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

        assertEquals(2, interview.getApplicationId());
        assertEquals(InterviewType.BEHAVIORAL.getTypeId(), interview.getType().getTypeId());
        assertEquals(InterviewResult.PASS.getResultId(), interview.getResult().getResultId());
        LocalDateTime expected = LocalDateTime.of(2024, 2, 22, 3, 14, 7);
        assertEquals(expected, interview.getWhen());
        assertEquals("OK feelings", interview.getNotes());
    }

    @Test
    void shouldNotFindMissingID() throws DataException {
        Interview interview = repository.findById(24);
        assertNull(interview);
    }

    @Test
    void shouldAddInterview() throws DataException {
        // All fields filled in
        Interview interview = makeInterview();

        interview = repository.add(interview);
        assertEquals(NEXT_ID, interview.getInterviewId());

        // null notes
        interview.setNotes(null);
        interview = repository.add(interview);
        assertEquals(NEXT_ID + 1, interview.getInterviewId());
    }

    @Test
    void shouldUpdateExistingInterview() throws DataException {
        Interview interview = makeInterview();
        interview.setInterviewId(3);

        assertTrue(repository.update(interview));
    }

    @Test
    void shouldNotUpdateMissingInterview() throws DataException {
        Interview interview = makeInterview();
        interview.setInterviewId(30);

        assertFalse(repository.update(interview));
    }

    @Test
    void shouldDeleteExistingInterview() throws DataException {
        assertTrue(repository.deleteById(4));
    }

    @Test
    void shouldNotDeleteMissingInterview() throws DataException {
        assertFalse(repository.deleteById(40));
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