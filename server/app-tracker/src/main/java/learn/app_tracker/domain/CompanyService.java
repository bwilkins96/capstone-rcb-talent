package learn.app_tracker.domain;

import learn.app_tracker.data.CompanyRepository;
import learn.app_tracker.models.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() {
        return repository.findAll();
    };

    public Company findById(int companyId) {
        return repository.findById(companyId);
    };

}
