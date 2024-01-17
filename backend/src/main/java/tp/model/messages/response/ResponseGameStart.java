package tp.model.messages.response;

import lombok.Data;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GameSettings;

public class ResponseGameStart extends ResponseMessage {
    public GameSettings content;

    public ResponseGameStart(MessageStatus status) {
        super(MessageType.GAME_START, status);
    }
}
