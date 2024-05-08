package utils;

import learn.app_tracker.models.Company;
import learn.app_tracker.models.JobApplication;
import learn.app_tracker.models.JobPosting;
import learn.app_tracker.models.enums.Origin;
import learn.app_tracker.models.enums.Status;

import java.time.LocalDate;
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

    public static JobApplication getTestApplication() {
        JobApplication application = new JobApplication();

        application.setStatus(Status.PENDING);
        application.setOrigin(Origin.COLD_APPLY);
        application.setDateApplied(LocalDate.of(2023, 1, 20));
        application.setNotes("I wonder what will happen");

        JobPosting posting = new JobPosting();
        posting.setPostingId(3);
        application.setPosting(posting);

        return application;
    }

    public static JobApplication getTestApplicationFull() {
        JobApplication application = getTestApplication();
        application.setPosting(getTestPostingFull());
        return application;
    }

    // (posting_id, status_id, origin_id, date_applied, notes)
    // (3, 1, 1, '2024-01-20', 'I wonder what will happen')

}
