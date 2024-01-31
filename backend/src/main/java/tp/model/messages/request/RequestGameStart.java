package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;
import tp.model.messages.shared.GameSettings;

@Data
public class RequestGameStart extends RequestMessage {
    private GameSettings content;

    public RequestGameStart(GameSettings gameSettings) {
        content = gameSettings;
    }

    @Override
    public MessageType getType() {
        return MessageType.GAME_START;
    }
}
