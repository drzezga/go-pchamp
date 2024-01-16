package tp.messages.request;

import tp.game.core.Position;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestGameMove extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_MOVE;
    }

    public record Content(String player, Position position) {}
}
