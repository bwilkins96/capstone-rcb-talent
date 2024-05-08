package learn.app_tracker.models.enums;

public enum InterviewType {

    BEHAVIORAL(1),
    TECHNICAL(2);

    private final int typeId;

    InterviewType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public static InterviewType findById(int typeId) {
        for (InterviewType type : InterviewType.values()) {
            if (type.typeId == typeId) {
                return type;
            }
        }
        String message = String.format("No InterviewType with value: %s", typeId);
        throw new RuntimeException(message);
    }
}
