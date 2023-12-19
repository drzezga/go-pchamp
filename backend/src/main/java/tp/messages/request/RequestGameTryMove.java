package tp.messages.request;

import tp.game.Position;
import tp.messages.RequestMessage;

public class RequestGameTryMove extends RequestMessage {
    public record Content(Position position) { }

    public Content content;

    public Position getPosition() {
        if (this.content == null) return null;
        return this.content.position();
    }
}
