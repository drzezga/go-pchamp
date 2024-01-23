package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GameSettings;

public class ResponseGameStart extends ResponseMessage {
    @Getter
    @Setter
    private GameSettings content;

    public ResponseGameStart(MessageStatus status) {
        super(status);
    }
}
