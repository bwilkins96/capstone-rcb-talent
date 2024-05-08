package learn.app_tracker.data.mappers;

import learn.app_tracker.models.Interview;
import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.InterviewResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InterviewMapper implements  RowMapper<Interview> {
    @Override
    public Interview mapRow(ResultSet resultSet, int i) throws SQLException {
        Interview iv = new Interview();
        iv.setInterviewId(resultSet.getInt("interview_id"));
        iv.setApplicationId((resultSet.getInt("application_id")));
        iv.setType(InterviewType.findById(resultSet.getInt("type_id")));
        iv.setResult(InterviewResult.findById(resultSet.getInt("result_id")));

        Timestamp timestamp = Timestamp.valueOf(resultSet.getString("when"));
        iv.setWhen(timestamp.toLocalDateTime());

        iv.setNotes(resultSet.getString("note"));
        return iv;
    }
}
