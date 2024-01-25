package tp.model.messages.response;

import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseGameLeave extends ResponseMessage<Object> {
    public ResponseGameLeave() {
        this.messageType = MessageType.GAME_LEAVE;
        this.status =  MessageStatus.OK;
    }
}
