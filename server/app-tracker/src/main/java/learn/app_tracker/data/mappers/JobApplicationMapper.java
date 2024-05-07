package learn.app_tracker.data.mappers;

import learn.app_tracker.models.JobApplication;
import learn.app_tracker.models.JobPosting;
import learn.app_tracker.models.enums.Origin;
import learn.app_tracker.models.enums.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobApplicationMapper implements RowMapper<JobApplication> {

    @Override
    public JobApplication mapRow(ResultSet resultSet, int i) throws SQLException {
        JobApplication application = new JobApplication();

        application.setApplicationId(resultSet.getInt("application_id"));
        application.setDateApplied(resultSet.getDate("date_applied").toLocalDate());
        application.setNotes(resultSet.getString("notes"));

        application.setStatus(Status.getById(resultSet.getInt("status_id")));
        application.setOrigin(Origin.getById(resultSet.getInt("origin_id")));

        JobPosting posting = new JobPosting();
        posting.setPostingId(resultSet.getInt("posting_id"));
        application.setPosting(posting);

        return application;
    }

}
