package tp.model.messages.response;

import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseGameLeave extends ResponseMessage {
    public ResponseGameLeave() {
        super(MessageStatus.OK);
    }
}
