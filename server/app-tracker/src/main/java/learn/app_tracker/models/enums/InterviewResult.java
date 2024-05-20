package learn.app_tracker.models.enums;

public enum InterviewResult {

    UNDETERMINED(1, "Undetermined"),
    PASS(2, "Pass"),
    FAIL(3, "Fail");

    private final int resultId;

    private final String text;

    InterviewResult(int resultId, String text) {
        this.resultId = resultId;
        this.text = text;
    }

    public int getResultId() {
        return resultId;
    }

    public String getText() {
        return text;
    }

    public static InterviewResult findById(int resultId) {
        for (InterviewResult interviewResult : InterviewResult.values()) {
            if (interviewResult.resultId == resultId) {
                return interviewResult;
            }
        }
        String message = String.format("No Result with value: %s", resultId);
        throw new RuntimeException(message);
    }
}
