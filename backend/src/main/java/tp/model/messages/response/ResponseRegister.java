package tp.model.messages.response;

import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseRegister extends ResponseMessage<String> {

    public ResponseRegister() {
        this.messageType = MessageType.REGISTER;
        this.status = MessageStatus.OK;
    }

    public ResponseRegister(MessageStatus status, String content) {
        this();
        this.content = content;
    }
}
