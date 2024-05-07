package learn.app_tracker.data;

import learn.app_tracker.data.mappers.CompanyMapper;
import learn.app_tracker.models.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyJdbcTemplateRepository implements CompanyRepository {

    private static final String FIELDS =
            "company_id, company_name, company_email, address, city, state, postal_code, company_phone";
    private final JdbcTemplate jdbcTemplate;

    public CompanyJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Company> findAll() {
        final String sql = "select " + FIELDS + " from company;";

        return jdbcTemplate.query(sql, new CompanyMapper());
    }

}
