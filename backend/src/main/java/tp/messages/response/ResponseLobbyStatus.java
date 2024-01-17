package tp.messages.response;

import lombok.Data;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseLobbyStatus extends ResponseMessage {
    public Content content;

    public ResponseLobbyStatus() {
        super(MessageType.LOBBY_STATUS, MessageStatus.OK);
    }

    @Data
    public class Content {
        private String name;
        private List<Player> players;
    }

    @Data
    public class Player {
        private String name;
        private Boolean isHost;
    }
}
