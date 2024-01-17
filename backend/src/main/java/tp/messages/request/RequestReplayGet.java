package tp.messages.request;

import lombok.Data;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestReplayGet extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_GET;
    }

    @Data
    public class Content {
        private String id;
    }
}
