package tp.feature.game;

import org.junit.jupiter.api.Test;
import tp.feature.client.ClientEvents;
import tp.feature.client.ClientRepository;
import tp.feature.game.bot.BotController;
import tp.feature.game.requestHandlers.GameStartHandler;
import tp.feature.lobby.LobbyController;
import tp.feature.lobby.LobbyEvents;
import tp.feature.lobby.LobbyTestUtils;
import tp.model.messages.request.RequestGameStart;
import tp.model.messages.shared.GameSettings;

import static org.junit.jupiter.api.Assertions.*;

public class GameStartHandlerTests {
    @Test
    public void testHandleGameStart() {
        LobbyController lobbyController = new LobbyController(new LobbyEvents());
        ClientEvents clientEvents = new ClientEvents();
        ClientRepository clientRepository = new ClientRepository(clientEvents);

        GameRepository gameRepository = new GameRepository();
        BotController botController = new BotController(clientRepository);
        GameController gameController = new GameController(clientRepository, botController, gameRepository);

        GameStartHandler handler = new GameStartHandler(lobbyController, clientRepository, gameController);

        lobbyController.joinLobby("test", LobbyTestUtils.CLIENT);

        GameSettings settings = new GameSettings();
        settings.setSize(19);
        settings.setStartingPlayer("test");
        settings.setBotOpponent(true);

        RequestGameStart message = new RequestGameStart(settings);

        handler.onMessage(message, LobbyTestUtils.CLIENT);

        assertTrue(gameRepository.getGameByClient(LobbyTestUtils.CLIENT).isPresent());

        assertFalse(gameRepository.getGameByClient(LobbyTestUtils.CLIENT).get().getGameState().isFinished());
    }

    @Test
    public void testStartingGameByGuest() {
        LobbyController lobbyController = new LobbyController(new LobbyEvents());
        ClientEvents clientEvents = new ClientEvents();
        ClientRepository clientRepository = new ClientRepository(clientEvents);

        GameRepository gameRepository = new GameRepository();
        BotController botController = new BotController(clientRepository);
        GameController gameController = new GameController(clientRepository, botController, gameRepository);

        GameStartHandler handler = new GameStartHandler(lobbyController, clientRepository, gameController);

        lobbyController.joinLobby("test", LobbyTestUtils.CLIENT);
        lobbyController.joinLobby("test", LobbyTestUtils.CLIENT_2);

        GameSettings settings = new GameSettings();
        settings.setSize(19);
        settings.setStartingPlayer("test");
        settings.setBotOpponent(true);

        RequestGameStart message = new RequestGameStart(settings);

        assertThrows(GameStartHandler.GuestCannotStartGameException.class, () -> {
            handler.onMessage(message, LobbyTestUtils.CLIENT_2);
        });
    }
}
