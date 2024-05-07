package learn.app_tracker.models;

import learn.app_tracker.models.enums.InterviewType;
import learn.app_tracker.models.enums.Result;

import java.time.LocalDateTime;
import java.util.Objects;

public class Interview {

    private int interviewId;
    private InterviewType type;
    private Result result;
    private LocalDateTime when;
    private String notes;

    public Interview() {

    }

    public Interview(int interviewId, InterviewType type, Result result, LocalDateTime when, String notes) {
        this.interviewId = interviewId;
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

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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
        return type == interview.type && result == interview.result && Objects.equals(when, interview.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, result, when);
    }

}
