package tp.lobby.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.game.Player;
import tp.game.WebsocketPlayer;
import tp.lobby.LobbyController;
import tp.messages.MessageHandler;
import tp.messages.MessageType;
import tp.messages.request.RequestLobbyList;
import tp.messages.response.ResponseLobbyList;

@Component
public class LobbyListHandler implements MessageHandler<RequestLobbyList> {
    LobbyController controller;
    WebsocketPlayer player;

    @Autowired
    public LobbyListHandler(LobbyController controller, WebsocketPlayer player) {
        this.controller = controller;
        this.player = player;
    }

    @Override
    public void onMessage(RequestLobbyList message) {
        var lobbies = controller.listLobbies();

        player.sendMessage(new ResponseLobbyList(lobbies));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_LIST;
    }
}
