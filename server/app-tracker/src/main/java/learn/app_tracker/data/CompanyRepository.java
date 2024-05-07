package learn.app_tracker.data;

import learn.app_tracker.models.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> findAll();

}
