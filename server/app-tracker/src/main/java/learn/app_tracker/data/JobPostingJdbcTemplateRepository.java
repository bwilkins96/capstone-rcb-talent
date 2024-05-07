package learn.app_tracker.data;

import learn.app_tracker.data.mappers.CompanyMapper;
import learn.app_tracker.data.mappers.JobPostingMapper;
import learn.app_tracker.data.mappers.SkillMapper;
import learn.app_tracker.models.Company;
import learn.app_tracker.models.JobPosting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobPostingJdbcTemplateRepository implements JobPostingRepository {

    private static final String FIELDS =
            "posting_id, company_id, `role`, `level`, visa_sponsorship, degree";

    private final JdbcTemplate jdbcTemplate;
    private final CompanyRepository companyRepository;

    public JobPostingJdbcTemplateRepository(JdbcTemplate jdbcTemplate, CompanyRepository companyRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<JobPosting> findAll() {
        final String sql = "select " + FIELDS + " from job_posting;";

        return jdbcTemplate.query(sql, new JobPostingMapper());
    }

    @Override
    public JobPosting findById(int postingId) {
        JobPosting posting = findByIdBase(postingId);

        if (posting != null) {
            addSkills(posting);
            addCompany(posting);
        }

        return posting;
    }

    private JobPosting findByIdBase(int postingId) {
        final String sql = "select " + FIELDS + " from job_posting where posting_id = ?;";

        return jdbcTemplate.query(sql, new JobPostingMapper(), postingId)
                .stream().findFirst().orElse(null);
    }

    private void addSkills(JobPosting posting) {
        final String sql = "select s.skill_name skill_name " +
                "from job_posting jp " +
                "join posting_skill ps on jp.posting_id = ps.posting_id " +
                "join skill s on ps.skill_id = s.skill_id " +
                "where jp.posting_id = ?;";

        List<String> skills = jdbcTemplate.query(sql, new SkillMapper(), posting.getPostingId());
        posting.setSkills(new ArrayList<>(skills));
    }

    private void addCompany(JobPosting posting) {
        int companyId = posting.getCompany().getCompanyId();

        Company company = companyRepository.findById(companyId);
        posting.setCompany(company);
    }

}
