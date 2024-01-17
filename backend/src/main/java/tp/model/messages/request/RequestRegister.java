package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

@Data
public class RequestRegister extends RequestMessage {
    private String content;

    @Override
    public MessageType getType() {
        return MessageType.REGISTER;
    }
}
