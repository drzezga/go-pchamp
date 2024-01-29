package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.Position;

import java.util.List;

public class ResponseGameMove extends ResponseMessage<ResponseGameMove.Content> {

    public ResponseGameMove(MessageStatus status, Content content) {
        this.messageType = MessageType.GAME_MOVE;
        this.status =  status;
        this.content = content;
    }

    @Data
    @AllArgsConstructor
    public static class Content {
        private String player;
        private Position position;
        private List<Position> captures;
    }
}
