package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseGameStart extends ResponseMessage {
    public Content content;

    public ResponseGameStart(MessageStatus status) {
        super(MessageType.GAME_START, status);
    }

    public record Content(Integer size, Boolean botOpponent, String startingPlayer) {}
}
