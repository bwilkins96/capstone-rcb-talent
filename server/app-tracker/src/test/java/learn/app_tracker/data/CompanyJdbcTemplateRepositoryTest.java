package learn.app_tracker.data;

import learn.app_tracker.models.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.getHtdTalent;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CompanyJdbcTemplateRepositoryTest {

    @Autowired
    CompanyJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Company> result = repository.findAll();

        assertEquals(3, result.size());
        assertTrue(result.contains(getHtdTalent()));
    }

    @Test
    void shouldFindById() {
        Company actual = repository.findById(1);

        assertEquals(actual, getHtdTalent());
    }

}