package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseGameLeave extends ResponseMessage {
    public ResponseGameLeave() {
        super(MessageType.GAME_LEAVE, MessageStatus.OK);
    }
}
