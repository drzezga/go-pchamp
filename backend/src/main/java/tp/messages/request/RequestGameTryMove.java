package tp.messages.request;

import tp.game.core.Position;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestGameTryMove extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_TRY_MOVE;
    }

    public Position getPosition() {
        if (this.content == null) return null;
        return this.content.position();
    }

    public record Content(Position position) {}
}
