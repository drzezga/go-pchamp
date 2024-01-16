package tp.messages.request;

import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestReplayList extends RequestMessage {
    public Object content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_LIST;
    }
}
