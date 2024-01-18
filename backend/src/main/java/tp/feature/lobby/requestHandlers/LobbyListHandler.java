package tp.feature.lobby.requestHandlers;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyRegistry;
import tp.communication.RequestMessageHandler;
import tp.communication.MessageType;
import tp.feature.player.Player;
import tp.model.messages.request.RequestLobbyList;
import tp.model.messages.response.ResponseLobbyList;

import java.util.Collection;

@Controller
public class LobbyListHandler implements RequestMessageHandler<RequestLobbyList> {
    private final LobbyRegistry lobbyRegistry;

    @Autowired
    public LobbyListHandler(LobbyRegistry lobbyRegistry) {
        this.lobbyRegistry = lobbyRegistry;
    }

    @Override
    public void onMessage(RequestLobbyList message, Player sender) {
        Collection<Lobby> lobbies = lobbyRegistry.getAllLobbies();

        sender.getMessageChannel().sendResponse(new ResponseLobbyList(lobbies));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_LIST;
    }
}
