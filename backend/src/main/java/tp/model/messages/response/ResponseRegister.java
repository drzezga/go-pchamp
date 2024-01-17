package tp.model.messages.response;

import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseRegister extends ResponseMessage {
    public ResponseRegister() {
        super(MessageType.REGISTER, MessageStatus.OK);
    }
}
