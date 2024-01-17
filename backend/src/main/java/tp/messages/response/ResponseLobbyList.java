package tp.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.Collection;
import java.util.List;

public class ResponseLobbyList extends ResponseMessage {
    public List<Lobby> content;

    public ResponseLobbyList(Collection<tp.lobby.Lobby> lobbies) {
        super(MessageType.LOBBY_LIST, MessageStatus.OK);
        this.content = lobbies
                .stream()
                .map(lobby -> new Lobby(lobby.getLobbyName(), lobby.getPlayerCount()))
                .toList();
    }

    @Data
    @AllArgsConstructor
    public class Lobby {
        private String name;
        private Integer playerCount;
    }
}
