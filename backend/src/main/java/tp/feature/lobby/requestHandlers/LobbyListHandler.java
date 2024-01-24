package tp.feature.lobby.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.communication.RequestMessageHandler;
import tp.communication.MessageType;
import tp.feature.client.Client;
import tp.model.messages.request.RequestLobbyList;
import tp.model.messages.response.ResponseLobbyList;

import java.util.Collection;

@Controller
public class LobbyListHandler implements RequestMessageHandler<RequestLobbyList> {
    private final LobbyController lobbyRegistry;

    @Autowired
    public LobbyListHandler(LobbyController lobbyRegistry) {
        this.lobbyRegistry = lobbyRegistry;
    }

    @Override
    public void onMessage(RequestLobbyList message, Client sender) {
        Collection<Lobby> lobbies = lobbyRegistry.getAllLobbies();

        sender.getMessageChannel().sendResponse(new ResponseLobbyList(lobbies));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_LIST;
    }
}
