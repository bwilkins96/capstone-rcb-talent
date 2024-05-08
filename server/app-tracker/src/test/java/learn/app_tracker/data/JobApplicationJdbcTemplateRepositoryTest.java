package learn.app_tracker.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    }

    @Test
    void shouldFindById() {

    }

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldDeleteById() {

    }

}