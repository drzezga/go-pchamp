package tp.feature.lobby.requestHandlers;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import tp.communication.RequestMessageHandler;
import tp.communication.MessageType;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyRegistry;
import tp.feature.player.Player;
import tp.feature.player.PlayerRegistry;
import tp.model.messages.request.RequestLobbyStatus;
import tp.model.messages.response.ResponseLobbyStatus;

import java.util.Optional;

@Controller
public class LobbyStatusHandler implements RequestMessageHandler<RequestLobbyStatus> {
    private final PlayerRegistry playerRegistry;
    private final LobbyRegistry lobbyRegistry;

    @Autowired
    public LobbyStatusHandler(PlayerRegistry playerRegistry, LobbyRegistry lobbyRegistry) {
        this.playerRegistry = playerRegistry;
        this.lobbyRegistry = lobbyRegistry;
    }

    @Override
    public void onMessage(RequestLobbyStatus message, Player sender) {
        switch(message.getContent().getAction()) {
            case JOIN -> handleJoinLobby(message, sender);
            case LEAVE -> handleLeaveLobby(message, sender);
        }
        System.out.println(lobbyRegistry.getAllLobbies());
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_STATUS;
    }

    private void handleJoinLobby(RequestLobbyStatus message, Player sender) {
        String lobbyName = message.getContent().getName();

        Optional<Lobby> optionalLobby = lobbyRegistry.getLobbyByName(lobbyName);
        String playerName = sender.getName();

        Lobby lobby;
        if(optionalLobby.isPresent()) {
            lobby = optionalLobby.get();
            lobby.setGuest(Optional.of(playerName));
        } else {
            lobby = lobbyRegistry.addNewLobby(lobbyName);
            lobby.setHost(Optional.of(playerName));
        }

        sender.getMessageChannel().sendResponse(convertLobbyToResponseMessage(lobby));
    }

    private void handleLeaveLobby(RequestLobbyStatus message, Player sender) {
        String lobbyName = message.getContent().getName();

        Lobby lobby = lobbyRegistry.getLobbyByName(lobbyName).get();
        String senderName = sender.getName();

        if(lobby.getHost().equals(senderName)) {
            lobby.setHost(lobby.getGuest());
            lobby.setGuest(Optional.empty());
        } else {
            lobby.setGuest(Optional.empty());
        }

        String hostName = lobby.getHost().get();
        Optional<Player> optionalHostWebsocket = playerRegistry.getPlayerByName(hostName);

        optionalHostWebsocket.ifPresent(
                player -> player.getMessageChannel().sendResponse(convertLobbyToResponseMessage(lobby))
        );

        sender.getMessageChannel().sendResponse(new ResponseLobbyStatus());
    }

    private ResponseLobbyStatus convertLobbyToResponseMessage(Lobby lobby) {
        var responseContent = new ResponseLobbyStatus.Content();
        responseContent.setName(lobby.getLobbyName());

        if(lobby.getHost().isPresent()) {
            responseContent.getPlayers().add(
                    new ResponseLobbyStatus.Player(lobby.getHost().get(), true)
            );
        }

        if(lobby.getGuest().isPresent()) {
            responseContent.getPlayers().add(
                    new ResponseLobbyStatus.Player(lobby.getGuest().get(), false)
            );
        }

        return new ResponseLobbyStatus(responseContent);
    }
}
