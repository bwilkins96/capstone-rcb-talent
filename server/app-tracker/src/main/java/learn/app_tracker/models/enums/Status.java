package learn.app_tracker.models.enums;

public enum Status {

    OFFER(1, "Offer"),
    REJECTION(2, "Rejection"),
    PENDING(3, "Pending"),
    NO_RESPONSE(4, "No Response"),
    WITHDRAWN(5, "Withdrawn");

    private final int statusId;
    private final String text;

    Status(int statusId, String text) {
        this.statusId = statusId;
        this.text = text;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getText() {
        return text;
    }

    public static Status getById(int statusId) {
        for (Status status : Status.values()) {
            if (status.statusId == statusId) {
                return status;
            }
        }

        return null;
    }

}
