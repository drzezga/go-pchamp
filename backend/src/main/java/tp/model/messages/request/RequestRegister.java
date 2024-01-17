package tp.model.messages.request;

import tp.communication.MessageType;

public class RequestRegister extends RequestMessage {
    public Object content;

    @Override
    public MessageType getType() {
        return MessageType.REGISTER;
    }
}
