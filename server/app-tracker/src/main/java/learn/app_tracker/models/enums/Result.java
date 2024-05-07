package learn.app_tracker.models.enums;

public enum Result {

    UNDETERMINED(1),
    PASS(2),
    FAIL(3);

    private final int value;

    Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Result findByValue(int value) {
        for (Result result : Result.values()) {
            if (result.value == value) {
                return result;
            }
        }
        String message = String.format("No Result with value: %s", value);
        throw new RuntimeException(message);
    }
}
