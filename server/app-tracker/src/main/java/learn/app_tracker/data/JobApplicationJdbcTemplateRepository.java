package learn.app_tracker.data;

import learn.app_tracker.models.JobApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JobApplicationJdbcTemplateRepository implements JobApplicationRepository {

    private static final String FIELDS =
            "application_id, posting_id, status_id, origin_id, date_applied, notes";
    private final JdbcTemplate jdbcTemplate;

    public JobApplicationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JobApplication> findAll() {
        return null;
    }

    @Override
    public JobApplication findById(int applicationId) {
        return null;
    }

    @Override
    public JobApplication add(JobApplication application) {
        return null;
    }

    @Override
    public boolean update(JobApplication application) {
        return false;
    }

    @Override
    public boolean deleteById(int applicationId) {
        return false;
    }

}
