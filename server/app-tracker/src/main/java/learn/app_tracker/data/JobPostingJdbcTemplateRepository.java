package learn.app_tracker.data;

import learn.app_tracker.data.mappers.JobPostingMapper;
import learn.app_tracker.models.JobPosting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobPostingJdbcTemplateRepository implements JobPostingRepository {

    private static final String FIELDS =
            "posting_id, company_id, `role`, `level`, visa_sponsorship, degree";
    private final JdbcTemplate jdbcTemplate;

    public JobPostingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JobPosting> findAll() {
        final String sql = "select " + FIELDS + " from job_posting;";

        return jdbcTemplate.query(sql, new JobPostingMapper());
    }

}
