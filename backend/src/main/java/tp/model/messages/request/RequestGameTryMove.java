package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;
import tp.model.Position;

@Data
public class RequestGameTryMove extends RequestMessage {
    private Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_TRY_MOVE;
    }

    public Position getPosition() {
        if (this.content == null) return null;
        return this.content.getPosition();
    }

    @Data
    public class Content {
        private Position position;
    }
}
