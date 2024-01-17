package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

@Data
public class RequestLobbyStatus extends RequestMessage {
    private Content content;

    @Override
    public MessageType getType() {
        return MessageType.LOBBY_STATUS;
    }

    @Data
    public class Content {
        private Action action;
        private String name;
    }

    public enum Action {
        JOIN,
        LEAVE,
    }
}
