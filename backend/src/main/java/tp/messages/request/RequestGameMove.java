package tp.messages.request;

import lombok.Data;
import tp.game.core.Position;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

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
