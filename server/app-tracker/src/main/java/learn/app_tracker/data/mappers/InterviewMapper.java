package learn.app_tracker.data.mappers;

import learn.app_tracker.models.Interview;
import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.InterviewResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InterviewMapper implements  RowMapper<Interview> {
    @Override
    public Interview mapRow(ResultSet resultSet, int i) throws SQLException {
        Interview iv = new Interview();
        iv.setInterviewId(resultSet.getInt("interview_id"));
        iv.setApplicationId((resultSet.getInt("application_id")));
        iv.setType(InterviewType.findById(resultSet.getInt("type_id")));
        iv.setResult(InterviewResult.findById(resultSet.getInt("result_id")));

        /* MySQL defaults the timezone to utc, so if we want the user to see the correct time in their timezone when
           they get the stored information back, we need to convert back to the system's local timezone.

           Note: this implementation relies on the fact that the user is running this application on their own personal
           device. If we as developers were to release this to the public and host it on a server, the implementation of
           times must be different to preserve the proper timezone
        */
        Timestamp timestamp = Timestamp.valueOf(resultSet.getString("when"));
        LocalDateTime utcDateTime = timestamp.toLocalDateTime();
        ZonedDateTime curTZDateTime = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
                .toOffsetDateTime()
                .atZoneSameInstant(ZoneId.systemDefault());
        iv.setWhen(curTZDateTime.toLocalDateTime());

        iv.setNotes(resultSet.getString("note"));
        return iv;
    }
}
