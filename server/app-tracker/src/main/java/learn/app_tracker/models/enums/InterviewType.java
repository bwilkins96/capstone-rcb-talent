package learn.app_tracker.models.enums;

public enum InterviewType {

    BEHAVIORAL(1),
    TECHNICAL(2);

    private final int value;

    InterviewType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InterviewType findByValue(int value) {
        for (InterviewType type : InterviewType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        String message = String.format("No InterviewType with value: %s", value);
        throw new RuntimeException(message);
    }
}
