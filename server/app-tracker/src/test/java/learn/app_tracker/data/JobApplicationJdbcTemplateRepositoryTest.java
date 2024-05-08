package learn.app_tracker.data;

import learn.app_tracker.models.JobApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.getTestApplication;
import static utils.TestUtils.getTestApplicationFull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JobApplicationJdbcTemplateRepositoryTest {

    @Autowired
    JobApplicationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<JobApplication> result = repository.findAll();

        assertTrue(result.size() >= 3);
        assertTrue(result.contains(getTestApplication()));
    }

    @Test
    void shouldFindById() {
        JobApplication actual = repository.findById(2);
        assertEquals(getTestApplicationFull(), actual);
    }

    @Test
    void shouldNotFindMissingId() {
        JobApplication actual = repository.findById(99);
        assertNull(actual);
    }

    @Test
    void shouldAdd() {
        JobApplication actual = getTestApplication();
        actual.setApplicationId(0);

        JobApplication expected = getTestApplication();
        expected.setApplicationId(5);

        actual = repository.add(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        JobApplication toUpdate = getTestApplicationFull();

        toUpdate.setApplicationId(4);
        toUpdate.setDateApplied(LocalDate.of(2024, 4, 4));
        toUpdate.setNotes("updated!");

        assertTrue(repository.update(toUpdate));
        assertEquals(toUpdate, repository.findById(4));
    }

    @Test
    void shouldNotUpdateMissing() {
        JobApplication missing = getTestApplication();
        missing.setApplicationId(99);

        assertFalse(repository.update(missing));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(4));
        assertNull(repository.findById(4));
    }

    @Test
    void shouldNotDeleteMissingId() {
        assertFalse(repository.deleteById(99));
    }

}