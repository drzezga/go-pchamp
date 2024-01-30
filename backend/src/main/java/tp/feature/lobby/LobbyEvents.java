package tp.feature.lobby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import tp.events.ServerEvent;
import tp.feature.client.Client;

@Getter
@Component
public class LobbyEvents {
    private ServerEvent<Lobby> destroyLobbyEvent = new ServerEvent<>();

    @Data
    @AllArgsConstructor
    public static class ClientLeaveEvent {
        private Lobby lobby;
        private Client client;
    }
}
