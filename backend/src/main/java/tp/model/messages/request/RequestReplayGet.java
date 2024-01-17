package tp.model.messages.request;

import lombok.Data;
import tp.model.Position;
import tp.communication.MessageType;
import tp.model.messages.shared.GamePlayer;
import tp.model.messages.shared.GameSettings;

import java.util.List;

@Data
public class RequestReplayGet extends RequestMessage {
    private Content content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_GET;
    }

    @Data
    public class Content {
        private String id;
        private String name;
        private List<GamePlayer> players;
        private List<Position> moves;
        private GameSettings gameSettings;
    }
}
