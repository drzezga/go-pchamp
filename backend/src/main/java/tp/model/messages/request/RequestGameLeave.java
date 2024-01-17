package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

public class RequestGameLeave extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_LEAVE;
    }

    @Data
    public class Content {
        private String name;
    }
}
