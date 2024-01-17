package tp.model.messages.request;

import tp.communication.MessageType;

public class RequestLobbyList extends RequestMessage {
    public Object content;

    @Override
    public MessageType getType() {
        return MessageType.LOBBY_LIST;
    }
}
