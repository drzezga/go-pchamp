package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.messages.shared.GamePlayer;

import java.util.List;

public class ResponseReplayList extends ResponseMessage {
    @Getter
    @Setter
    private List<ReplayMetadata> content;

    public ResponseReplayList() {
        super(MessageType.REPLAY_LIST, MessageStatus.OK);
    }

    @Data
    public class ReplayMetadata {
        private String id;
        private String name;
        private List<GamePlayer> players;
        private String startingPlayer;
    }
}
