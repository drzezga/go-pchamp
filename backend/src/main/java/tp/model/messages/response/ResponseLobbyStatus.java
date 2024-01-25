package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

import java.util.ArrayList;
import java.util.List;

public class ResponseLobbyStatus extends ResponseMessage<ResponseLobbyStatus.Content> {

    public ResponseLobbyStatus() {
        this.messageType = MessageType.LOBBY_STATUS;
        this.status = MessageStatus.OK;
    }

    public ResponseLobbyStatus(Content content) {
        this();
        this.setContent(content);
    }

    @Data
    public static class Content {
        private String name;
        private List<Player> players = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    public static class Player {
        private String name;
        private Boolean isHost;
    }
}
