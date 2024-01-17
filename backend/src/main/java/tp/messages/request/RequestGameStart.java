package tp.messages.request;

import lombok.Data;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestGameStart extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_START;
    }

    @Data
    public class Content {
        private Integer size;
        private Boolean botOpponent;
        private String staringPlayer;
    }
}
