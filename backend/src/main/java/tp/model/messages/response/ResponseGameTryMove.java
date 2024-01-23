package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseGameTryMove extends ResponseMessage {
    @Getter
    @Setter
    private Content content;

    public ResponseGameTryMove() {
        super(MessageStatus.NOT_OK);
    }

    public ResponseGameTryMove(String error) {
        super(MessageStatus.NOT_OK);
        content = new Content(error);
    }

    @Data
    @AllArgsConstructor
    public class Content {
        String error;
    }
}
