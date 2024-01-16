package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseRegister extends ResponseMessage {
    public ResponseRegister() {
        super(MessageType.REGISTER, MessageStatus.OK);
    }
}
