package learn.app_tracker.data.mappers;

import learn.app_tracker.models.JobPosting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPostingMapper implements RowMapper<JobPosting> {

    @Override
    public JobPosting mapRow(ResultSet resultSet, int i) throws SQLException {
        JobPosting posting = new JobPosting();

        posting.setPostingId(resultSet.getInt("posting_id"));
        posting.setRole(resultSet.getString("role"));
        posting.setLevel(resultSet.getString("level"));
        posting.setVisaSponsorship(resultSet.getBoolean("visa_sponsorship"));
        posting.setDegree(resultSet.getString("degree"));

        return posting;
    }

}
