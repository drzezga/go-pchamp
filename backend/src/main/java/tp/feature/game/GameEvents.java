package tp.feature.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import tp.events.ServerEvent;
import tp.feature.client.Client;

@Getter
@Component
public class GameEvents {
    private ServerEvent<ClientLeaveEvent> clientLeaveEventEvent = new ServerEvent<>();

    @Getter
    @AllArgsConstructor
    public static class ClientLeaveEvent {
        private Game game;
        private Client client;
    }
}
