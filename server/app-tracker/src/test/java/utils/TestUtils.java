package utils;

import learn.app_tracker.models.Company;
import learn.app_tracker.models.JobPosting;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static Company getHtdTalent() {
        return new Company(
                1,
                "HTD Talent",
                "htdtalent@email.com",
                "1234 Company Ave",
                "Dallas",
                "TX",
                "a-1234",
                "123-4567"
        );
    }

    public static JobPosting getTestPosting() {
        JobPosting posting = new JobPosting();

        posting.setPostingId(1);
        posting.setRole("Software Developer");
        posting.setLevel("Entry Level");
        posting.setVisaSponsorship(false);
        posting.setDegree("Bachelors");

        Company company = new Company();
        company.setCompanyId(1);
        posting.setCompany(company);

        return posting;
    }

    public static JobPosting getTestPostingFull() {
        JobPosting posting = getTestPosting();

        posting.setSkills(new ArrayList<>(
                List.of("Git", "Java", "Spring Boot")
        ));

        posting.setCompany(getHtdTalent());

        return posting;
    }

}
