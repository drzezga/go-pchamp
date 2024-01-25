package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GamePlayer;

import java.util.List;

public class ResponseGameFinished extends ResponseMessage<ResponseGameFinished.Content> {
    @Getter
    @Setter
    private Content content;

    public ResponseGameFinished() {
        this.messageType = MessageType.GAME_FINISHED;
        this.status =  MessageStatus.OK;
    }

    @Data
    public class Content {
        private List<GamePlayer> scores;
    }
}
