package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.ResponseMessage;

public class ResponseGameTryMove extends ResponseMessage {
    public record Content(String error) { }

    public Content content;

    public ResponseGameTryMove(String error) {
        super(MessageStatus.NOT_OK);
        content = new Content(error);
    }
}
