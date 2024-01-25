package tp.model.messages.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

import java.util.Collection;
import java.util.List;

public class ResponseLobbyList extends ResponseMessage<List<ResponseLobbyList.Lobby>> {

    public ResponseLobbyList(Collection<tp.feature.lobby.Lobby> lobbies) {
        this.messageType = MessageType.LOBBY_LIST;
        this.status = MessageStatus.OK;

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
