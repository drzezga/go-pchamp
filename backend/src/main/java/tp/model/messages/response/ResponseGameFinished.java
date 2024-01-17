package tp.model.messages.response;

import lombok.Data;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GamePlayer;

import java.util.List;

public class ResponseGameFinished extends ResponseMessage {
    public Content content;

    public ResponseGameFinished() {
        super(MessageType.GAME_FINISHED, MessageStatus.OK);
    }

    @Data
    public class Content {
        private List<GamePlayer> scores;
    }
}
