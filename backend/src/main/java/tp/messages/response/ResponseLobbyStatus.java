package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseLobbyStatus extends ResponseMessage {
    public Content content;

    public ResponseLobbyStatus() {
        super(MessageType.LOBBY_STATUS, MessageStatus.OK);
    }

    public record Content(String name, List<Player> players) {}
    public record Player(String name, Boolean isHost) {}
}
