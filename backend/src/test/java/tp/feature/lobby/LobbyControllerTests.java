package tp.feature.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp.feature.lobby.LobbyTestUtils.CLIENT;
import static tp.feature.lobby.LobbyTestUtils.CLIENT_2;

public class LobbyControllerTests {

    @Test
    public void testCreateLobby() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);
        lobbyController.joinLobby("test", CLIENT);

        assertTrue(lobbyController.getLobbyByName("test").isPresent());

        assertTrue(lobbyController.getLobbyByClient(CLIENT).isPresent());

        assertEquals(CLIENT, lobbyController.getLobbyByName("test").get().getHost().get());
    }

    @Test
    public void testJoinLobby() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);
        Lobby lobby = lobbyController.addNewLobby("test");
        lobbyController.joinLobby("test", CLIENT);

        assertEquals(lobby, lobbyController.getLobbyByName("test").get());

        assertEquals(lobby, lobbyController.getLobbyByClient(CLIENT).get());

        assertEquals(CLIENT, lobby.getGuest().get());
    }

    @Test
    public void testLeaveLobbyWithOnePlayer() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);
        Lobby lobby = lobbyController.addNewLobby("test");
        lobbyController.joinLobby("test", CLIENT);
        lobbyController.leaveLobby(CLIENT);

        assertTrue(lobbyController.getLobbyByName("test").isEmpty());

        assertTrue(lobbyController.getLobbyByClient(CLIENT).isEmpty());

        assertTrue(lobby.getHost().isEmpty());
    }

    @Test
    public void testChangeLobbyOwnership() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);

        Lobby lobby = lobbyController.joinLobby("test", CLIENT);

        lobbyController.joinLobby("test", CLIENT_2);

        assertEquals(CLIENT, lobby.getHost().get());

        assertEquals(CLIENT_2, lobby.getGuest().get());

        lobbyController.leaveLobby(CLIENT);

        assertEquals(CLIENT_2, lobby.getHost().get());

        assertTrue(lobby.getGuest().isEmpty());

        assertEquals(lobby, lobbyController.getLobbyByClient(CLIENT_2).get());

        assertTrue(lobbyController.getLobbyByClient(CLIENT).isEmpty());
    }
}
