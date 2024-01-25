package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.Position;

public class ResponseGameMove extends ResponseMessage<ResponseGameMove.Content> {

    public ResponseGameMove(MessageStatus status, String player, Position position) {
        this.messageType = MessageType.GAME_MOVE;
        this.status =  status;

        if (position == null) {
            this.content = null;
        } else {
            this.content = new Content(player, position);
        }
    }

    @Data
    @AllArgsConstructor
    public class Content {
        private String player;
        private Position position;
    }
}
