package tp.model.messages.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.Position;
import tp.model.messages.shared.GamePlayer;
import tp.model.messages.shared.GameSettings;

import java.util.List;

public class ResponseReplayGet extends ResponseMessage {
    @Getter
    @Setter
    private Content content;

    public ResponseReplayGet(MessageStatus status) {
        super(status);
    }

    @Data
    public class Content {
        private String id;
        private String name;
        private List<GamePlayer> players;
        private List<Position> move;
        private GameSettings gameSettings;
    }
}
