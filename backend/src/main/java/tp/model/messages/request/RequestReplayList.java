package tp.model.messages.request;

import tp.communication.MessageType;

public class RequestReplayList extends RequestMessage {
    public Object content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_LIST;
    }
}
