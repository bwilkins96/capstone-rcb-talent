package learn.app_tracker.models;

import learn.app_tracker.models.enums.Origin;
import learn.app_tracker.models.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class JobApplication {

    private int applicationId;
    private JobPosting posting;
    private Status status;
    private Origin origin;
    private LocalDate dateApplied;
    private String notes;
    private ArrayList<Interview> interviews;

    public JobApplication() {

    }

    public JobApplication(int applicationId, JobPosting posting, Status status, Origin origin, LocalDate dateApplied, String notes, ArrayList<Interview> interviews) {
        this.applicationId = applicationId;
        this.posting = posting;
        this.status = status;
        this.origin = origin;
        this.dateApplied = dateApplied;
        this.notes = notes;
        this.interviews = interviews;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public JobPosting getPosting() {
        return posting;
    }

    public void setPosting(JobPosting posting) {
        this.posting = posting;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<Interview> getInterviews() {
        return new ArrayList<>(interviews);
    }

    public void setInterviews(ArrayList<Interview> interviews) {
        this.interviews = interviews;
    }

    public boolean addInterview(Interview interview) {
        return interviews.add(interview);
    }

    public boolean removeInterview(Interview interview) {
        return interviews.remove(interview);
    }

    public Interview removeInterview(int idx) {
        return interviews.remove(idx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return applicationId == that.applicationId && Objects.equals(posting, that.posting) && status == that.status && origin == that.origin && Objects.equals(dateApplied, that.dateApplied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, posting, status, origin, dateApplied);
    }

}
