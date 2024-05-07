package learn.app_tracker.data.mappers;

import learn.app_tracker.models.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Company(
          resultSet.getInt("company_id"),
          resultSet.getString("company_name"),
          resultSet.getString("company_email"),
          resultSet.getString("address"),
          resultSet.getString("city"),
          resultSet.getString("state"),
          resultSet.getString("postal_code"),
          resultSet.getString("company_phone")
        );
    }

}
