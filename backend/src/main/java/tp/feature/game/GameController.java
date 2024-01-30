package tp.feature.game;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.game.bot.BotController;
import tp.feature.lobby.Lobby;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.response.ResponseMessage;
import tp.model.messages.shared.GameSettings;

import java.util.*;

@Log
@Controller
public class GameController {
    private final ClientRepository clientRepository;
    private final BotController botController;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(ClientRepository clientRepository, BotController botController, GameRepository gameRepository) {
        this.clientRepository = clientRepository;
        this.botController = botController;
        this.gameRepository = gameRepository;
    }

    public Game startGameFromLobby(Lobby lobby, GameSettings settings) {
        boolean isPlayingAgainstBot = settings.getBotOpponent();
        if(isPlayingAgainstBot) {
            return startGameAgainstBot(lobby, settings);
        } else {
            return gameRepository.registerGameAgainstAHuman(lobby, settings);
        }
    }

    public Game getGameByClient(Client client) {
        Game game = gameRepository.getGameByClient(client).orElseThrow(
                () -> new ClientNotInGameException(client)
        );

        return game;
    }

    public List<Position> makeMove(Client player, Position position) {
        Game game = getGameByClient(player);
        Piece playerPiece = getPlayerPiece(game, player);

        Move move = new Move(position, playerPiece);

        List<Position> capturedPieces = game.getGameState().makeMove(move);

        System.out.println(game.getGameState().getCurrentBoardState());

        if(game.getGameState().getSettings().getBotOpponent()) {
            botController.scheduleMove(game);
        }
        return capturedPieces;
    }

    public void broadcastMessageToPlayers(Game game, ResponseMessage<?> message) {
        Client blackClient = game.getBlackPlayer();
        Client whiteClient = game.getWhitePlayer();

        blackClient.getMessageChannel().sendResponse(message);
        whiteClient.getMessageChannel().sendResponse(message);
    }

    private Game startGameAgainstBot(Lobby lobby, GameSettings settings) {
        // TODO: Implement playing against a bot
        throw new UnsupportedOperationException("Playing against a bot hasnt yet been implemented");
    }

    private Piece getPlayerPiece(Game game, Client player) {

        if(player.equals(game.getWhitePlayer())) {
            return Piece.WHITE;
        }

        if(player.equals(game.getBlackPlayer())) {
            return Piece.BLACK;
        }

        throw new IllegalArgumentException(
                String.format("Client '%s' is not currently in specified game", player.getName())
        );
    }

    public class ClientNotInGameException extends RuntimeException {
        public ClientNotInGameException(Client client) {
            super(String.format("Client '%s' is not currently in a game", client.getName()));
        }
    }

    public class ClientAbsentFromGameException extends RuntimeException {
        public ClientAbsentFromGameException(String clientName) {
            super(String.format("Client '%s' is not currently in the specified game", clientName));
        }
    }
}
