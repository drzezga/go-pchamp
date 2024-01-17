package tp.messages.response;

import lombok.Data;
import tp.game.core.Position;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.List;

public class ResponseReplayGet extends ResponseMessage {
    public Content content;

    public ResponseReplayGet(MessageStatus status) {
        super(MessageType.REPLAY_GET, status);
    }

    @Data
    public class Content {
        private String id;
        private String name;
        private List<Player> players;
        private List<Position> move;
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
