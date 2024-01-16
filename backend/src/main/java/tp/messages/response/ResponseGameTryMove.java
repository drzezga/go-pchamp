package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseGameTryMove extends ResponseMessage {
    public Content content;

    public ResponseGameTryMove() {
        super(MessageType.GAME_TRY_MOVE, MessageStatus.NOT_OK);
    }

    public ResponseGameTryMove(String error) {
        super(MessageType.GAME_TRY_MOVE, MessageStatus.NOT_OK);
        content = new Content(error);
    }

    public record Content(String error) { }
}
