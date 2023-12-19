package tp.messages.response;

import tp.game.Position;
import tp.messages.MessageStatus;
import tp.messages.ResponseMessage;

public class ResponseGameMove extends ResponseMessage {
    record Content(String player, Position position) { }

    public Content content;

    public ResponseGameMove(MessageStatus status, String player, Position position) {
        super(status);
        this.content = new Content(player, position);
    }
}
