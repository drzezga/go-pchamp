package tp.feature.lobby;

import org.junit.jupiter.api.Test;
import tp.feature.client.ClientEvents;
import tp.feature.lobby.eventHandlers.ClientDisconnectHandler;
import tp.feature.lobby.eventHandlers.DestroyLobbyHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LobbyEventsTests {
    @Test
    public void testHandleDestroyLobby() {
        LobbyEvents events = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(events);

        Lobby lobby = lobbyController.joinLobby("test", LobbyTestUtils.CLIENT);

        assertEquals(lobbyController.getAllLobbies().size(), 1);

        DestroyLobbyHandler handler = new DestroyLobbyHandler(events, lobbyController);

        events.getDestroyLobbyEvent().dispatch(lobby);

        assertEquals(lobbyController.getAllLobbies().size(), 0);
    }

    @Test
    public void testHandleClientDisconnect() {
        ClientEvents clientEvents = new ClientEvents();
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);

        Lobby lobby = lobbyController.joinLobby("test", LobbyTestUtils.CLIENT);

        assertEquals(lobbyController.getAllLobbies().size(), 1);

        ClientDisconnectHandler handler = new ClientDisconnectHandler(clientEvents, lobbyController);

        clientEvents.getClientDisconnectEvent().dispatch(LobbyTestUtils.CLIENT);

        assertEquals(lobbyController.getAllLobbies().size(), 0);
    }
}
