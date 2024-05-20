package learn.app_tracker.data.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString("skill_name");
    }

}
