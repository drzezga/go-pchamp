package tp.messages.response;

import lombok.Data;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseGameStart extends ResponseMessage {
    public Content content;

    public ResponseGameStart(MessageStatus status) {
        super(MessageType.GAME_START, status);
    }

    @Data
    public class Content {
        private Integer size;
        private Boolean botOpponent;
        private String startingPlayer;
    }
}
