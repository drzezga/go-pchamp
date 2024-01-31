package tp.model.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import tp.communication.MessageType;

@Data
@NoArgsConstructor
public class RequestLobbyStatus extends RequestMessage {
    private Content content;

    public RequestLobbyStatus(Action action, String name) {
        this.content = new Content();
        this.content.action = action;
        this.content.name = name;
    }

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
