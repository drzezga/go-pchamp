package tp.messages.response;

import tp.game.core.Position;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseGameMove extends ResponseMessage {
    public Content content;

    public ResponseGameMove(MessageStatus status, String player, Position position) {
        super(MessageType.GAME_MOVE, status);
        if (position == null) {
            this.content = null;
        } else {
            this.content = new Content(player, position);
        }
    }

    public record Content(String player, Position position) { }
}
