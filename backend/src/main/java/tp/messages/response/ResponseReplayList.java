package tp.messages.response;

import lombok.Data;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseReplayList extends ResponseMessage {
    public List<ReplayMetadata> content;

    public ResponseReplayList() {
        super(MessageType.REPLAY_LIST, MessageStatus.OK);
    }

    @Data
    public class ReplayMetadata {
        private String id;
        private String name;
        private List<Player> players;
        private String startingPlayer;
    }

    @Data
    public class Player {
        private String name;
        private Integer score;
    }
}
