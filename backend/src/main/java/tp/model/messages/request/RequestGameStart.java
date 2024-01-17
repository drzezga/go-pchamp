package tp.model.messages.request;

import lombok.Data;
import tp.communication.MessageType;
import tp.model.messages.shared.GameSettings;

public class RequestGameStart extends RequestMessage {
    public GameSettings content;

    @Override
    public MessageType getType() {
        return MessageType.GAME_START;
    }
}
