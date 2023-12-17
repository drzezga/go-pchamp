package tp.messages;

public enum MessageStatus {
    OK, NOT_OK;

    @Override
    public String toString() {
        switch (this) {
            case OK -> { return "ok"; }
            case NOT_OK -> { return "not ok"; }
        }
        return null;
    }
}
