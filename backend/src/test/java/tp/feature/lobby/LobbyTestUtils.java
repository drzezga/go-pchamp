package tp.feature.lobby;

import tp.feature.client.Client;
import tp.feature.client.ClientMessageChannel;
import tp.model.messages.response.ResponseMessage;

public class LobbyTestUtils {
    public static final Client CLIENT = new Client(
            new ClientMessageChannel() {
                @Override
                public <T extends ResponseMessage> void sendResponse(T response) {}
            },
            "test"
    );

    public  static final Client CLIENT_2 = new Client(
            new ClientMessageChannel() {
                @Override
                public <T extends ResponseMessage> void sendResponse(T response) {}
            },
            "test_2"
    );
}
