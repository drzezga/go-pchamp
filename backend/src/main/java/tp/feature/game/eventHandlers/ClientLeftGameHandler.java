package tp.feature.game.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.feature.game.Game;
import tp.feature.game.GameEvents;
import tp.feature.game.GameRepository;
import tp.feature.lobby.LobbyController;
import tp.feature.lobby.LobbyEvents;
import tp.model.messages.response.ResponseGameLeave;

@Component
public class ClientLeftGameHandler {
    private final GameRepository gameRepository;
    private final LobbyEvents lobbyEvents;
    private final LobbyController lobbyController;

    @Autowired
    public ClientLeftGameHandler(GameEvents gameEvents,
                                 GameRepository gameRepository,
                                 LobbyEvents lobbyEvents,
                                 LobbyController lobbyController) {
        this.gameRepository = gameRepository;
        this.lobbyEvents = lobbyEvents;
        this.lobbyController = lobbyController;
        gameEvents.getClientLeaveEventEvent().subscribe(this::handleClientLeaveGame);
    }

    private void handleClientLeaveGame(GameEvents.ClientLeaveEvent event) {
        destroyGame(event);
        destroyLobby(event);
    }

    private void destroyGame(GameEvents.ClientLeaveEvent event) {
        Game game = event.getGame();

        var response = new ResponseGameLeave();
        game.getWhitePlayer().getMessageChannel().sendResponse(response);
        game.getBlackPlayer().getMessageChannel().sendResponse(response);

        gameRepository.removeGame(game);
    }

    private void destroyLobby(GameEvents.ClientLeaveEvent event) {
        lobbyEvents.getDestroyLobbyEvent().dispatch(
                lobbyController.getLobbyByClient(event.getClient()).get()
        );
    }
}
