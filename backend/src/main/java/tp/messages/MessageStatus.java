package tp.messages;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageStatus {
    OK, NOT_OK;

    @Override
    @JsonValue
    public String toString() {
        switch (this) {
            case OK -> { return "ok"; }
            case NOT_OK -> { return "not ok"; }
        }
        return null;
    }
}
