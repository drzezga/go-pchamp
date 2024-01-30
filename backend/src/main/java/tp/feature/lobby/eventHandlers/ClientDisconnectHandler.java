package tp.feature.lobby.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.feature.client.Client;
import tp.feature.client.ClientEvents;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.feature.lobby.LobbyEvents;

import java.util.Optional;

@Component
public class ClientDisconnectHandler {
    private final LobbyEvents lobbyEvents;
    private final LobbyController lobbyController;

    @Autowired
    public ClientDisconnectHandler(ClientEvents clientEvents, LobbyEvents lobbyEvents, LobbyController lobbyController) {
        this.lobbyEvents = lobbyEvents;
        this.lobbyController = lobbyController;
        clientEvents.getClientDisconnectEvent().subscribe(this::handleClientDisconnect);
    }

    private void handleClientDisconnect(Client client) {
        Optional<Lobby> optionalLobby = lobbyController.getLobbyByClient(client);

        if(optionalLobby.isEmpty()) {
            return;
        }

        lobbyController.leaveLobby(client);

//        lobbyEvents.getClientLeaveEvent().dispatch(new LobbyEvents.ClientLeaveEvent(
//                optionalLobby.get(),
//                client
//        ));
    }
}
