package tp.model.messages.request;

import lombok.Data;
import tp.model.Position;
import tp.communication.MessageType;

public class RequestGameMove extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_MOVE;
    }

    @Data
    public class Content {
        private String player;
        private Position position;
    }
}
