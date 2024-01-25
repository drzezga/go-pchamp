package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseGameTryMove extends ResponseMessage<ResponseGameTryMove.Content> {

    public ResponseGameTryMove() {
        this.messageType = MessageType.GAME_TRY_MOVE;
        this.status = MessageStatus.NOT_OK;
    }

    public ResponseGameTryMove(String error) {
        this();
        content = new Content(error);
    }

    @Data
    @AllArgsConstructor
    public class Content {
        String error;
    }
}
