package tp.feature.game.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.feature.game.Game;
import tp.feature.game.GameEvents;
import tp.feature.game.GameRepository;
import tp.model.messages.response.ResponseGameLeave;

@Component
public class ClientLeftGameHandler {
    private final GameRepository gameRepository;

    @Autowired
    public ClientLeftGameHandler(GameEvents gameEvents, GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        gameEvents.getClientLeaveEventEvent().subscribe(this::handleGameDestroyed);
    }

    public void handleGameDestroyed(GameEvents.ClientLeaveEvent event) {
        Game game = event.getGame();

        var response = new ResponseGameLeave();
        game.getWhitePlayer().getMessageChannel().sendResponse(response);
        game.getBlackPlayer().getMessageChannel().sendResponse(response);

        gameRepository.removeGame(game);
    }
}
