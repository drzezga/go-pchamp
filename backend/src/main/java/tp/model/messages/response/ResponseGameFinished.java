package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GamePlayer;

import java.util.List;

public class ResponseGameFinished extends ResponseMessage {
    @Getter
    @Setter
    private Content content;

    public ResponseGameFinished() {
        super(MessageStatus.OK);
    }

    @Data
    public class Content {
        private List<GamePlayer> scores;
    }
}
