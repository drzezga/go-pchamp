package tp.feature.game.requestHandlers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.game.Game;
import tp.feature.game.GameEvents;
import tp.feature.game.GameController;
import tp.model.messages.request.RequestGameLeave;

@Log
@Controller
public class GameLeaveHandler implements RequestMessageHandler<RequestGameLeave> {
    private final GameController gameController;
    private final GameEvents gameEvents;

    @Autowired
    public GameLeaveHandler(GameController gameController, GameEvents gameEvents) {
        this.gameController = gameController;
        this.gameEvents = gameEvents;
    }

    @Override
    public void onMessage(RequestGameLeave message, Client sender) {
        Game game = gameController.getGameByClient(sender);

        gameEvents.getClientLeaveEventEvent().dispatch(new GameEvents.ClientLeaveEvent(
                game,
                sender
        ));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_LEAVE;
    }
}
