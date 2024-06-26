package learn.app_tracker.data;

import learn.app_tracker.data.mappers.CompanyMapper;
import learn.app_tracker.data.mappers.JobApplicationMapper;
import learn.app_tracker.models.Interview;
import learn.app_tracker.models.JobApplication;
import learn.app_tracker.models.JobPosting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JobApplicationJdbcTemplateRepository implements JobApplicationRepository {

    private static final String FIELDS =
            "application_id, posting_id, status_id, origin_id, date_applied, notes";

    private final JdbcTemplate jdbcTemplate;
    private final JobPostingRepository jobPostingRepository;
    private final InterviewRepository interviewRepository;

    public JobApplicationJdbcTemplateRepository(JdbcTemplate jdbcTemplate, JobPostingRepository jobPostingRepository, InterviewRepository interviewRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.jobPostingRepository = jobPostingRepository;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public List<JobApplication> findAll() {
        final String sql = "select " + FIELDS + " from application;";

        return jdbcTemplate.query(sql, new JobApplicationMapper());
    }

    @Override
    @Transactional
    public JobApplication findById(int applicationId) {
        JobApplication application = findByIdBase(applicationId);

        if (application != null) {
            addPosting(application);
            addInterviews(application);
        }

        return application;
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
                "notes = ?" +
                "where application_id = ?;";

        return jdbcTemplate.update(
                sql,
                application.getPosting().getPostingId(),
                application.getStatus().getStatusId(),
                application.getOrigin().getOriginId(),
                application.getDateApplied().toString(),
                application.getNotes(),
                application.getApplicationId()
        ) > 0;
    }

    @Override
    public boolean deleteById(int applicationId) {
        final String sql = "delete from application where application_id = ?";

        return jdbcTemplate.update(sql, applicationId) > 0;
    }

    private JobApplication findByIdBase(int applicationId) {
        final String sql = "select " + FIELDS + " from application where application_id = ?;";

        return jdbcTemplate.query(sql, new JobApplicationMapper(), applicationId)
                .stream().findFirst().orElse(null);
    }

    private JobApplication addPosting(JobApplication application) {
        JobPosting posting = jobPostingRepository.findById(application.getPosting().getPostingId());
        application.setPosting(posting);
        return application;
    }

    private JobApplication addInterviews(JobApplication application) {
        List<Interview> interviews = interviewRepository.findAllByApplicationId(application.getApplicationId());

        if (interviews != null) {
            application.setInterviews(new ArrayList<>(interviews));
        }

        return application;
    }

}
