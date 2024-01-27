package tp.feature.game.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.game.Game;
import tp.feature.game.GameController;
import tp.model.Move;
import tp.model.Position;
import tp.model.messages.request.RequestGameTryMove;
import tp.model.messages.response.ResponseGameMove;

@Component
public class GameTryMoveHandler implements RequestMessageHandler<RequestGameTryMove> {
    private final GameController gameController;

    @Autowired
    public GameTryMoveHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onMessage(RequestGameTryMove message, Client sender) {
        Position position = message.getContent().getPosition();
        gameController.makeMove(sender, position);

        Game game = gameController.getGameByClient(sender);
        gameController.broadcastMessageToPlayers(
                game,
                new ResponseGameMove(MessageStatus.OK, sender.getName(), position)
        );
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_TRY_MOVE;
    }
}
