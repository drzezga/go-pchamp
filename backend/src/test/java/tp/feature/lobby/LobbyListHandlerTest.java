package tp.feature.lobby;

import org.junit.jupiter.api.Test;
import tp.feature.client.Client;
import tp.feature.client.ClientMessageChannel;
import tp.feature.lobby.requestHandlers.LobbyListHandler;
import tp.model.messages.request.RequestLobbyList;
import tp.model.messages.response.ResponseLobbyList;
import tp.model.messages.response.ResponseMessage;

import java.util.ArrayList;


public class LobbyListHandlerTest {
    public static Client CLIENT = new Client(
            new ClientMessageChannel() {
                @Override
                public <T extends ResponseMessage> void sendResponse(T response) {}
            },
            "test"
    );
    @Test
    public void testListLobbies() {
        LobbyEvents lobbyEvents = new LobbyEvents();
        LobbyController lobbyController = new LobbyController(lobbyEvents);
        lobbyController.joinLobby("test", CLIENT);

        LobbyListHandler lobbyListHandler = new LobbyListHandler(lobbyController);

        ArrayList<ResponseLobbyList> responses = new ArrayList<>();

        Client CLIENT_3 = new Client(
                new ClientMessageChannel() {
                    @Override
                    public <T extends ResponseMessage> void sendResponse(T response) {
                        responses.add((ResponseLobbyList) response);
                    }
                },
                "test"
        );

        lobbyListHandler.onMessage(new RequestLobbyList(), CLIENT_3);

        assert(responses.size() == 1);

        assert(responses.get(0).content.size() == 1);

        assert(responses.get(0).content.get(0).getName().equals("test"));
    }
}
