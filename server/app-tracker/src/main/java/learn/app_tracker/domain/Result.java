package learn.app_tracker.domain;

import java.util.ArrayList;

public class Result<T> {

    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType type = ResultType.SUCCESS;
    private T Payload;

    public ArrayList<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public ResultType getType() {
        return type;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

    public T getPayload() {
        return Payload;
    }

    public void setPayload(T payload) {
        Payload = payload;
    }

    public void addMessage(String message, ResultType type) {
        messages.add(message);
        this.type = type;
    }

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

}
