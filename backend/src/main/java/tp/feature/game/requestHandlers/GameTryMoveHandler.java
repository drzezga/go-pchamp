package tp.feature.game.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.game.Game;
import tp.feature.game.GameController;
import tp.feature.game.GameRepository;
import tp.feature.game.scoreCalculation.GameScoreCalculator;
import tp.feature.game.scoreCalculation.GameScores;
import tp.model.Position;
import tp.model.messages.request.RequestGameTryMove;
import tp.model.messages.response.ResponseGameFinished;
import tp.model.messages.response.ResponseGameMove;
import tp.model.messages.shared.GamePlayer;

import java.util.List;

@Component
public class GameTryMoveHandler implements RequestMessageHandler<RequestGameTryMove> {
    private final GameController gameController;
    private final GameRepository gameRepository;
    private final GameScoreCalculator gameScoreCalculator;

    @Autowired
    public GameTryMoveHandler(GameController gameController, GameRepository gameRepository, GameScoreCalculator gameScoreCalculator) {
        this.gameController = gameController;
        this.gameRepository = gameRepository;
        this.gameScoreCalculator = gameScoreCalculator;
    }

    @Override
    public void onMessage(RequestGameTryMove message, Client sender) {
        Position position = message.getContent().getPosition();
        List<Position> capturedPositions = gameController.makeMove(sender, position);

        Game game = gameController.getGameByClient(sender);

        var moveMessageContent = new ResponseGameMove.Content(
                sender.getName(),
                position,
                capturedPositions
        );
        gameController.broadcastMessageToPlayers(game, new ResponseGameMove(MessageStatus.OK, moveMessageContent));

        if(!game.getGameState().isFinished()) {
            return;
        }

        GameScores scores = gameScoreCalculator.calculateScores(game.getGameState());

        var gameFinishedContent = new ResponseGameFinished.Content(List.of(
                new GamePlayer(game.getBlackPlayer().getName(), scores.getBlackPlayerScore()),
                new GamePlayer(game.getWhitePlayer().getName(), scores.getWhitePlayerScore())
        ));
        gameController.broadcastMessageToPlayers(game, new ResponseGameFinished(gameFinishedContent));
        // TODO: Implement saving game replay
        // gameController.saveReplay(game);
        gameRepository.removeGame(game);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_TRY_MOVE;
    }
}
