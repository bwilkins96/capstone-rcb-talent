package learn.app_tracker.models;

import java.util.ArrayList;
import java.util.Objects;

public class JobPosting {

    private int postingId;
    private Company company;
    private String role;
    private String level;
    private boolean visaSponsorship;
    private String degree;
    private ArrayList<String> skills = new ArrayList<>();

    public JobPosting() {

    }

    public JobPosting(int postingId, Company company, String role, String level, boolean visaSponsorship, String degree, ArrayList<String> skills) {
        this.postingId = postingId;
        this.company = company;
        this.role = role;
        this.level = level;
        this.visaSponsorship = visaSponsorship;
        this.degree = degree;
        this.skills = skills;
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

    public ArrayList<String> getSkills() {
        return new ArrayList<>(skills);
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public boolean addSkill(String skill) {
        return skills.add(skill);
    }

    public boolean removeSkill(String skill) {
        return skills.remove(skill);
    }

    public String removeSkill(int idx) {
        return skills.remove(idx);
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                "postingId=" + postingId +
                ", company=" + company +
                ", role='" + role + '\'' +
                ", level='" + level + '\'' +
                ", visaSponsorship=" + visaSponsorship +
                ", degree='" + degree + '\'' +
                ", skills=" + skills +
                '}';
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
