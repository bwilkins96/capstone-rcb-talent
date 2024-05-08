package learn.app_tracker.data;

import learn.app_tracker.data.mappers.InterviewMapper;
import learn.app_tracker.models.Interview;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class InterviewJdbcTemplateRepository implements InterviewRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String INTERVIEW_COLUMN_NAMES = "interview_id, application_id, type_id, result_id, `when`, note";


    public InterviewJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Interview> findAll() {
        final String sql = String.format("select %s from interview;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new InterviewMapper());
    }

    @Override
    public List<Interview> findAllByApplicationId(int appId) {
        final String sql = String.format("select %s from interview " + "where application_id = ?;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new InterviewMapper(), appId);
    }

    @Override
    public Interview findById(int id) {
        final String sql = String.format("select %s from interview " + "where interview_id = ?;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new InterviewMapper(), id).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Interview add(Interview interview) {
        final String sql = "insert into interview " +
                "(application_id, type_id, result_id, `when`, note) " +
                "values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, interview.getApplicationId());
            statement.setInt(2, interview.getType().getTypeId());
            statement.setInt(3, interview.getResult().getResultId());
            statement.setTimestamp(4, Timestamp.valueOf(interview.getWhen()));
            statement.setString(5, interview.getNotes());
            return statement;
        }, keyHolder);

        if(rowsAffected == 0) {
            return null;
        }

        interview.setInterviewId(keyHolder.getKey().intValue());

        return interview;
    }

    @Override
    public boolean update(Interview interview) {
        final String sql = "update interview set " +
                "application_id = ?, " +
                "type_id = ?, " +
                "result_id = ?, " +
                "`when` = ?, " +
                "note = ? " +
                "where interview_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                interview.getApplicationId(),
                interview.getType().getTypeId(),
                interview.getResult().getResultId(),
                Date.valueOf(interview.getWhen().toLocalDate()),
                interview.getNotes(),
                interview.getInterviewId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "delete from interview where interview_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
