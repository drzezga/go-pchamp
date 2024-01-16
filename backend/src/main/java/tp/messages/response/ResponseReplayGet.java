package tp.messages.response;

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

    public record Content(
            String id,
            String name,
            List<Player> players,
            List<Position> move,
            GameSettings gameSettings
    ) {}

    public record Player(String name, Integer score) {}
    public record GameSettings(Integer size, Boolean botOpponent, String startingPlayer) {}
}
