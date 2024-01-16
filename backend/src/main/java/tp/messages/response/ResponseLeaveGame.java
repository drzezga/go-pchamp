package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

public class ResponseLeaveGame extends ResponseMessage {
    public ResponseLeaveGame() {
        super(MessageType.GAME_LEAVE, MessageStatus.OK);
    }
}
