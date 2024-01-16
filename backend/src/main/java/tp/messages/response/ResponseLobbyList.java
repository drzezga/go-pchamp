package tp.messages.response;

import tp.game.core.Position;
import tp.lobby.Lobby;
import tp.messages.MessageStatus;
import tp.messages.MessageType;
import tp.messages.ResponseMessage;

import java.util.Collection;
import java.util.List;

public class ResponseLobbyList extends ResponseMessage {
    public record Content(String name, int playerCount) { }

    public List<Content> content;

    public ResponseLobbyList(Collection<Lobby> lobbies) {
        super(MessageType.LOBBY_LIST, MessageStatus.OK);
        this.content = lobbies
                .stream()
                .map(lobby -> new Content(lobby.name, 1))
                .toList();
    }
}
