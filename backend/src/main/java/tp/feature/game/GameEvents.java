package tp.feature.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import tp.events.ServerEvent;
import tp.feature.client.Client;
import tp.model.Move;

@Getter
@Component
public class GameEvents {
    private ServerEvent<ClientLeaveEvent> clientLeaveEvent = new ServerEvent<>();
    private ServerEvent<PostMoveEvent> postMoveEvent = new ServerEvent<>();

    @Getter
    @AllArgsConstructor
    public static class ClientLeaveEvent {
        private Game game;
        private Client client;
    }

    @Getter
    @AllArgsConstructor
    public static class PostMoveEvent {
        private Game game;
        private Move move;
    }
}
