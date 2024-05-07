package learn.app_tracker.data;

import learn.app_tracker.models.Interview;
import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.Result;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final RowMapper<Interview> mapper = (rs, i) -> {
        Interview iv = new Interview();
        iv.setInterviewId(rs.getInt("interview_id"));
        iv.setApplicationId((rs.getInt("application_id")));
        iv.setType(InterviewType.findByValue(rs.getInt("type_id")));
        iv.setResult(Result.findByValue(rs.getInt("result_id")));
        Timestamp timestamp = new Timestamp(rs.getDate("when").getTime());
        iv.setWhen(timestamp.toLocalDateTime());
        iv.setNotes(rs.getString("note"));
        return iv;
    };

    public InterviewJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Interview> findAll() throws DataException {
        final String sql = String.format("select %s from interview;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Interview> findAllByApplicationId(int appId) throws DataException {
        final String sql = String.format("select %s from interview " + "where application_id = ?;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, mapper, appId);
    }

    @Override
    public Interview findById(int id) throws DataException {
        final String sql = String.format("select %s from interview " + "where interview_id = ?;", INTERVIEW_COLUMN_NAMES);
        return jdbcTemplate.query(sql, mapper, id).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Interview add(Interview interview) throws DataException {
        final String sql = "insert into interview " +
                "(application_id, type_id, result_id, `when`, note) " +
                "values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, interview.getApplicationId());
            statement.setInt(2, interview.getType().getValue());
            statement.setInt(3, interview.getResult().getValue());
            statement.setDate(4, Date.valueOf(interview.getWhen().toLocalDate()));
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
    public boolean update(Interview interview) throws DataException {
        final String sql = "update interview set " +
                "application_id = ?, " +
                "type_id = ?, " +
                "result_id = ?, " +
                "`when` = ?, " +
                "note = ? " +
                "where interview_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                interview.getApplicationId(),
                interview.getType().getValue(),
                interview.getResult().getValue(),
                Date.valueOf(interview.getWhen().toLocalDate()),
                interview.getNotes(),
                interview.getInterviewId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataException {
        final String sql = "delete from interview where interview_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}