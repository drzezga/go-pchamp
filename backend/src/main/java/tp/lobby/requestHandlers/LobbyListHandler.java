package tp.lobby.requestHandlers;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import tp.lobby.Lobby;
import tp.lobby.LobbyRegistry;
import tp.messages.MessageHandler;
import tp.messages.MessageType;
import tp.messages.WebsocketExtensions;
import tp.messages.request.RequestLobbyList;
import tp.messages.response.ResponseLobbyList;

import java.util.Collection;

@Controller
@ExtensionMethod({WebsocketExtensions.class})
public class LobbyListHandler implements MessageHandler<RequestLobbyList> {
    private final LobbyRegistry lobbyRegistry;

    @Autowired
    public LobbyListHandler(LobbyRegistry lobbyRegistry) {
        this.lobbyRegistry = lobbyRegistry;
    }

    @Override
    public void onMessage(RequestLobbyList message, WebSocketSession sender) {
        Collection<Lobby> lobbies = lobbyRegistry.getAllLobbies();

        sender.sendResponse(new ResponseLobbyList(lobbies));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_LIST;
    }
}
