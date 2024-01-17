package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

@Data
public class RequestGameLeave extends RequestMessage {
    private Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_LEAVE;
    }

    @Data
    public class Content {
        private String name;
    }
}
