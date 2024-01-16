package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseGameFinished extends ResponseMessage {
    public Content content;

    public ResponseGameFinished() {
        super(MessageType.GAME_FINISHED, MessageStatus.OK);
    }

    public record Content(List<Score> scores) {}
    public record Score(String name, Integer score) {}
}