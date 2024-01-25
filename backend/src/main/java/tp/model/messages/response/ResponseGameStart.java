package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GameSettings;

public class ResponseGameStart extends ResponseMessage<GameSettings> {
    public ResponseGameStart(MessageStatus status) {
        this.messageType = MessageType.GAME_START;
        this.status = status;
    }
}
