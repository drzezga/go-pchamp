package tp.model.messages.response;

import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

public class ResponseRegister extends ResponseMessage {
    @Getter
    @Setter
    private String content;


    public ResponseRegister() {
        super(MessageType.REGISTER, MessageStatus.OK);
    }

    public ResponseRegister(String content) {
        super(MessageType.REGISTER, MessageStatus.OK);
        this.content = content;
    }
}
