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
import tp.feature.game.persistence.GameRecord;
import tp.feature.game.persistence.ReplayRepository;
import tp.feature.game.scoreCalculation.GameScoreCalculator;
import tp.feature.game.scoreCalculation.GameScores;
import tp.feature.lobby.LobbyController;
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
    private final LobbyController lobbyController;
    private final ReplayRepository replayRepository;

    @Autowired
    public GameTryMoveHandler(GameController gameController,
                              GameRepository gameRepository,
                              GameScoreCalculator gameScoreCalculator,
                              LobbyController lobbyController,
                              ReplayRepository replayRepository) {
        this.gameController = gameController;
        this.gameRepository = gameRepository;
        this.gameScoreCalculator = gameScoreCalculator;
        this.lobbyController = lobbyController;
        this.replayRepository = replayRepository;
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
        List<GamePlayer> scoreList = List.of(
                new GamePlayer(game.getBlackPlayer().getName(), scores.getBlackPlayerScore()),
                new GamePlayer(game.getWhitePlayer().getName(), scores.getWhitePlayerScore())
        );
        var gameFinishedContent = new ResponseGameFinished.Content(scoreList);
        gameController.broadcastMessageToPlayers(game, new ResponseGameFinished(gameFinishedContent));


        String lobbyName = lobbyController.getLobbyByClient(sender).get().getLobbyName();
        GameRecord gameRecord = new GameRecord();
        gameRecord.setName(lobbyName);
        gameRecord.setGameSettings(game.getGameState().getSettings());
        gameRecord.setPlayers(scoreList);
        gameRecord.setMoves(game.getGameState().getMoves());
        replayRepository.save(gameRecord);

        gameRepository.removeGame(game);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_TRY_MOVE;
    }
}
