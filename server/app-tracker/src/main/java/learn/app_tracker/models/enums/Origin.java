package learn.app_tracker.models.enums;

public enum Origin {

    COLD_APPLY(1, "Cold Apply"),
    REFERRAL(2, "Referral"),
    CAREER_FAIR(3, "Career Fair");

    private final int originId;
    private final String text;

    Origin(int originId, String text) {
        this.originId = originId;
        this.text = text;
    }

    public int getOriginId() {
        return originId;
    }

    public String getText() {
        return text;
    }

    public static Origin getById(int originId) {
        for (Origin origin : Origin.values()) {
            if (origin.originId == originId) {
                return origin;
            }
        }

        return null;
    }

}
