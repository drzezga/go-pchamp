package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

import java.util.List;

public class ResponseLobbyStatus extends ResponseMessage {
    @Getter
    @Setter
    private Content content;

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
