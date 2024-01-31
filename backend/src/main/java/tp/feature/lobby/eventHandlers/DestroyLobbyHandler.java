package tp.feature.lobby.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.feature.lobby.LobbyEvents;

@Component
public class DestroyLobbyHandler {
    private final LobbyController lobbyController;

    @Autowired
    public DestroyLobbyHandler(LobbyEvents lobbyEvents, LobbyController lobbyController) {
        this.lobbyController = lobbyController;
        lobbyEvents.getDestroyLobbyEvent().subscribe(this::handleDestroyLobby);
    }

    private void handleDestroyLobby(Lobby lobby) {
        lobbyController.removeLobby(lobby);
    }
}
