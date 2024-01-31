package tp.model.messages.response;

import lombok.*;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.model.Position;
import tp.model.messages.shared.GamePlayer;
import tp.model.messages.shared.GameSettings;

import java.util.List;

public class ResponseReplayGet extends ResponseMessage<ResponseReplayGet.Content> {

    public ResponseReplayGet(Content content) {
        this.messageType = MessageType.REPLAY_GET;
        this.status = MessageStatus.OK;
        this.content = content;
    }

    @Data
    public static class Content {
        private String id;
        private String name;
        private List<GamePlayer> players;
        private List<Position> moves;
        private List<BoardState> boardStates;
        private GameSettings gameSettings;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardState {
        private List<Position> black;
        private List<Position> white;
    }
}
