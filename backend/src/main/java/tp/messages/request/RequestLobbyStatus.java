package tp.messages.request;

import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestLobbyStatus extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.LOBBY_STATUS;
    }

    public record Content(Action action, String name) {}
    public enum Action {
        JOIN,
        LEAVE,
    }
}
