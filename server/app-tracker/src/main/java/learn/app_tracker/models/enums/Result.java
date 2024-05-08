package learn.app_tracker.models.enums;

public enum Result {

    UNDETERMINED(1, "Undetermined"),
    PASS(2, "Pass"),
    FAIL(3, "Fail");

    private final int resultId;

    private final String text;

    Result(int resultId, String text) {
        this.resultId = resultId;
        this.text = text;
    }

    public int getResultId() {
        return resultId;
    }

    public String getText() {
        return text;
    }

    public static Result findById(int resultId) {
        for (Result result : Result.values()) {
            if (result.resultId == resultId) {
                return result;
            }
        }
        String message = String.format("No Result with value: %s", resultId);
        throw new RuntimeException(message);
    }
}
