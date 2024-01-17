package tp.messages.request;

import lombok.Data;
import tp.game.core.Position;
import tp.messages.MessageType;
import tp.messages.RequestMessage;

import java.util.List;

public class RequestReplayGet extends RequestMessage {
    public Content content;

    @Override
    public MessageType getType() {
        return MessageType.REPLAY_GET;
    }

    @Data
    public class Content {
        private String id;
        private String name;
        private List<Player> players;
        private List<Position> moves;
        private GameSettings gameSettings;
    }

    @Data
    public class Player {
        private String name;
        private Integer score;
    }

    @Data
    public class GameSettings {
        private Integer size;
        private Boolean botOpponent;
        private String startingPlayer;
    }
}
