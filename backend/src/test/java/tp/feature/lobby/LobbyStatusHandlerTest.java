package tp.feature.lobby;

import org.junit.jupiter.api.Test;
import tp.communication.MessageStatus;
import tp.feature.client.Client;
import tp.feature.client.ClientEvents;
import tp.feature.client.ClientMessageChannel;
import tp.feature.client.ClientRepository;
import tp.feature.lobby.requestHandlers.LobbyListHandler;
import tp.feature.lobby.requestHandlers.LobbyStatusHandler;
import tp.model.messages.request.RequestLobbyList;
import tp.model.messages.request.RequestLobbyStatus;
import tp.model.messages.response.ResponseLobbyList;
import tp.model.messages.response.ResponseLobbyStatus;
import tp.model.messages.response.ResponseMessage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static tp.feature.lobby.LobbyListHandlerTest.CLIENT;

public class LobbyStatusHandlerTest {
    @Test
    public void testJoinStatus() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);
        lobbyController.joinLobby("test", CLIENT);

        LobbyStatusHandler lobbyListHandler = new LobbyStatusHandler(lobbyController);

        ArrayList<ResponseLobbyStatus> responses = new ArrayList<>();

        Client CLIENT_3 = new Client(
                new ClientMessageChannel() {
                    @Override
                    public <T extends ResponseMessage> void sendResponse(T response) {
                        responses.add((ResponseLobbyStatus) response);
                    }
                },
                "test"
        );

        RequestLobbyStatus message = new RequestLobbyStatus(RequestLobbyStatus.Action.JOIN, "test");

        lobbyListHandler.onMessage(message, CLIENT_3);

        assertEquals(responses.size(), 1);

        assertEquals(responses.get(0).content.getName(), "test");

        assertEquals(responses.get(0).content.getPlayers().size(), 2);
    }

    @Test
    public void testLeaveStatus() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);

        LobbyStatusHandler lobbyListHandler = new LobbyStatusHandler(lobbyController);

        ArrayList<ResponseLobbyStatus> responses = new ArrayList<>();

        Client CLIENT_3 = new Client(
                new ClientMessageChannel() {
                    @Override
                    public <T extends ResponseMessage> void sendResponse(T response) {
                        responses.add((ResponseLobbyStatus) response);
                    }
                },
                "test"
        );

        lobbyController.joinLobby("test", CLIENT_3);

        RequestLobbyStatus message = new RequestLobbyStatus(RequestLobbyStatus.Action.LEAVE, null);

        lobbyListHandler.onMessage(message, CLIENT_3);

        assertEquals(responses.size(), 1);

        assertEquals(responses.get(0).status, MessageStatus.OK);

        assertNull(responses.get(0).content);
    }
}
