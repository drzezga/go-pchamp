package tp.messages.request;

import tp.messages.MessageType;
import tp.messages.RequestMessage;

public class RequestGameStart extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_START;
    }

    public record Content(Integer size, Boolean botOpponent, String startingPlayer) {}
}
