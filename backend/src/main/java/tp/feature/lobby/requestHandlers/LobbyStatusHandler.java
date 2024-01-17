package tp.feature.lobby.requestHandlers;

import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import tp.communication.RequestMessageHandler;
import tp.communication.MessageType;
import tp.communication.WebsocketExtensions;
import tp.model.messages.request.RequestLobbyStatus;

@Controller
@ExtensionMethod({WebsocketExtensions.class})
public class LobbyStatusHandler implements RequestMessageHandler<RequestLobbyStatus> {


    @Override
    public void onMessage(RequestLobbyStatus message, WebSocketSession sender) {
        String lobbyName = message.getContent().getName();

        switch(message.getContent().getAction()) {
            case JOIN -> System.out.println("Joining " + lobbyName);
            case LEAVE -> System.out.println("Leaving " + lobbyName);
        }
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_STATUS;
    }
}
