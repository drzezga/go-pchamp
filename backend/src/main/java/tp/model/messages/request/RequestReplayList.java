package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;

@Data
public class RequestReplayList extends RequestMessage {
    private Object content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_LIST;
    }
}
