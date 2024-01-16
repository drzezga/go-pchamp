package tp.messages.request;

import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestReplayGet extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_GET;
    }

    public record Content(String id) {}
}
