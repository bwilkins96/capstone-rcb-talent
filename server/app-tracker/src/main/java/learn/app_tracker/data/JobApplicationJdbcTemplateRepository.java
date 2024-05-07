package learn.app_tracker.data;

import learn.app_tracker.data.mappers.CompanyMapper;
import learn.app_tracker.data.mappers.JobApplicationMapper;
import learn.app_tracker.models.JobApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
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
        final String sql = "select " + FIELDS + " from application;";

        return jdbcTemplate.query(sql, new JobApplicationMapper());
    }

    @Override
    public JobApplication findById(int applicationId) {
        final String sql = "select " + FIELDS + " from application where application_id = ?;";

        return jdbcTemplate.query(sql, new JobApplicationMapper())
                .stream().findFirst().orElse(null);
    }

    @Override
    public JobApplication add(JobApplication application) {
        final String sql = "insert into application " +
                "(posting_id, status_id, origin_id, date_applied, notes) " +
                "values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, application.getPosting().getPostingId());
            ps.setInt(2, application.getStatus().getStatusId());
            ps.setInt(3, application.getOrigin().getOriginId());
            ps.setString(4, application.getDateApplied().toString());
            ps.setString(5, application.getNotes());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        application.setApplicationId(keyHolder.getKey().intValue());
        return application;
    }

    @Override
    public boolean update(JobApplication application) {
        final String sql = "update application set " +
                "posting_id = ?, " +
                "status_id = ?, " +
                "origin_id = ?, " +
                "date_applied = ?, " +
                "notes = ?;";

        return jdbcTemplate.update(
                sql,
                application.getPosting().getPostingId(),
                application.getStatus().getStatusId(),
                application.getOrigin().getOriginId(),
                application.getDateApplied().toString(),
                application.getNotes()
        ) > 0;
    }

    @Override
    public boolean deleteById(int applicationId) {
        final String sql = "delete from application where application_id = ?";

        return jdbcTemplate.update(sql, applicationId) > 0;
    }

}
