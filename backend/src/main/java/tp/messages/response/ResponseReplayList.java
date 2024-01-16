package tp.messages.response;

import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseReplayList extends ResponseMessage {
    public List<ReplayMetadata> content;

    public ResponseReplayList() {
        super(MessageType.REPLAY_LIST, MessageStatus.OK);
    }

    public record ReplayMetadata(String id, String name, List<Player> players, String startingPlayer) {}
    public record Player(String name, Integer score) {}
}
