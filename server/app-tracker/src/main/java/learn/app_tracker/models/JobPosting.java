package learn.app_tracker.models;

import java.util.Objects;

public class JobPosting {

    private int postingId;
    private Company company;
    private String role;
    private String level;
    private boolean visaSponsorship;
    private String degree;

    public JobPosting() {

    }

    public JobPosting(int postingId, Company company, String role, String level, boolean visaSponsorship, String degree) {
        this.postingId = postingId;
        this.company = company;
        this.role = role;
        this.level = level;
        this.visaSponsorship = visaSponsorship;
        this.degree = degree;
    }

    public int getPostingId() {
        return postingId;
    }

    public void setPostingId(int postingId) {
        this.postingId = postingId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isVisaSponsorship() {
        return visaSponsorship;
    }

    public void setVisaSponsorship(boolean visaSponsorship) {
        this.visaSponsorship = visaSponsorship;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPosting that = (JobPosting) o;
        return postingId == that.postingId && visaSponsorship == that.visaSponsorship && Objects.equals(company, that.company) && Objects.equals(role, that.role) && Objects.equals(level, that.level) && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postingId, company, role, level, visaSponsorship, degree);
    }

}
