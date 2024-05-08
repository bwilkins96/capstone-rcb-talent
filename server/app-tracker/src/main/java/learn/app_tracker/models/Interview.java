package learn.app_tracker.models;

import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.InterviewResult;

import java.time.LocalDateTime;
import java.util.Objects;

public class Interview {

    private int interviewId;
    private int applicationId;
    private InterviewType type;
    private InterviewResult result;
    private LocalDateTime when;
    private String notes;

    public Interview() {

    }

    public Interview(int interviewId, int applicationId, InterviewType type, InterviewResult result, LocalDateTime when, String notes) {
        this.interviewId = interviewId;
        this.applicationId = applicationId;
        this.type = type;
        this.result = result;
        this.when = when;
        this.notes = notes;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    public InterviewResult getResult() {
        return result;
    }

    public void setResult(InterviewResult result) {
        this.result = result;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return interviewId == interview.interviewId && applicationId == interview.applicationId && type == interview.type && result == interview.result && Objects.equals(when, interview.when) && Objects.equals(notes, interview.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewId, applicationId, type, result, when, notes);
    }

}
