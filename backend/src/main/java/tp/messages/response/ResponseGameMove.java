package tp.messages.response;

import tp.game.core.Position;
import tp.messages.MessageStatus;
import tp.messages.ResponseMessage;

public class ResponseGameMove extends ResponseMessage {
    public record Content(String player, Position position) { }

    public Content content;

    public ResponseGameMove(MessageStatus status, String player, Position position) {
        super(status);
        if (position == null) {
            this.content = null;
        } else {
            this.content = new Content(player, position);
        }
    }
}
