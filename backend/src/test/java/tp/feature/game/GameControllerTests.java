package tp.feature.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.feature.client.Client;
import tp.feature.client.ClientEvents;
import tp.feature.client.ClientMessageChannel;
import tp.feature.client.ClientRepository;
import tp.feature.game.bot.BotController;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.feature.lobby.LobbyEvents;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.response.ResponseMessage;
import tp.model.messages.shared.GameSettings;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tp.feature.lobby.LobbyTestUtils.CLIENT;
import static tp.feature.lobby.LobbyTestUtils.CLIENT_2;

public class GameControllerTests {
    @Test
    public void testStartGameFromLobby() {
        ClientRepository clientRepository = new ClientRepository(new ClientEvents());
        BotController botController = new BotController(clientRepository);
        GameRepository gameRepository = new GameRepository();

        GameController gameController = new GameController(clientRepository, botController, gameRepository);

        LobbyController lobbyController = new LobbyController(new LobbyEvents());

        Lobby lobby = lobbyController.joinLobby("test", CLIENT);

        GameSettings settings = new GameSettings();
        settings.setBotOpponent(true);
        settings.setSize(69);
        settings.setStartingPlayer("Bot");

        Game game = gameController.startGameFromLobby(lobby, settings);

        assertFalse(game.getGameState().isFinished());

        assertEquals(game.getGameState().getSettings().getSize(), 69);

        assertEquals(game.getGameState().getSettings().getStartingPlayer(), "Bot");

        assertEquals(game.getGameState().getSettings().getBotOpponent(), true);
    }

    @Test
    public void testMakeMove() {
        ClientRepository clientRepository = new ClientRepository(new ClientEvents());
        BotController botController = new BotController(clientRepository);
        GameRepository gameRepository = new GameRepository();

        GameController gameController = new GameController(clientRepository, botController, gameRepository);

        LobbyController lobbyController = new LobbyController(new LobbyEvents());

        Lobby lobby = lobbyController.joinLobby("test", CLIENT);

        GameSettings settings = new GameSettings();
        settings.setBotOpponent(true);
        settings.setSize(19);
        settings.setStartingPlayer("test");

        Game game = gameController.startGameFromLobby(lobby, settings);

        lobbyController.joinLobby("test", CLIENT_2);

        gameController.makeMove(CLIENT, new Position(0, 0));

        assertEquals(game.getGameState().getCurrentBoardState().getPiece(new Position(0, 0)), Piece.BLACK);
    }
}
