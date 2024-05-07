package learn.app_tracker.data;

import learn.app_tracker.models.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyJdbcTemplateRepository implements CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    public CompanyJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Company> findAll() {
        return null;
    }

}
