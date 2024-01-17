package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

public class RequestLobbyStatus extends RequestMessage {
    public Content content;

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
