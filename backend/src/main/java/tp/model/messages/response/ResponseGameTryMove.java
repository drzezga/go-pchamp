package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseGameTryMove extends ResponseMessage {
    public Content content;

    public ResponseGameTryMove() {
        super(MessageType.GAME_TRY_MOVE, MessageStatus.NOT_OK);
    }

    public ResponseGameTryMove(String error) {
        super(MessageType.GAME_TRY_MOVE, MessageStatus.NOT_OK);
        content = new Content(error);
    }

    @Data
    @AllArgsConstructor
    public class Content {
        String error;
    }
}
