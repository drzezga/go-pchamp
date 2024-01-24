package tp.feature.lobby.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.RequestMessageHandler;
import tp.communication.MessageType;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.model.messages.request.RequestLobbyStatus;
import tp.model.messages.response.ResponseLobbyStatus;

import java.util.Optional;

@Controller
public class LobbyStatusHandler implements RequestMessageHandler<RequestLobbyStatus> {
    private final ClientRepository clientRepository;
    private final LobbyController lobbyController;

    @Autowired
    public LobbyStatusHandler(ClientRepository playerRegistry, LobbyController lobbyRegistry) {
        this.clientRepository = playerRegistry;
        this.lobbyController = lobbyRegistry;
    }

    @Override
    public void onMessage(RequestLobbyStatus message, Client sender) {
        switch(message.getContent().getAction()) {
            case JOIN -> handleJoinLobby(message, sender);
            case LEAVE -> handleLeaveLobby(message, sender);
        }
        System.out.println(lobbyController.getAllLobbies());
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOBBY_STATUS;
    }

    private void handleJoinLobby(RequestLobbyStatus message, Client sender) {
        String lobbyName = message.getContent().getName();
        Lobby lobby = lobbyController.joinLobby(lobbyName, sender);

        ResponseLobbyStatus response = convertLobbyToResponseMessage(lobby);
        Client host = clientRepository.getClientByName(lobby.getHost().get()).get();
        host.getMessageChannel().sendResponse(response);

        if(lobby.getGuest().isEmpty()) {
            return;
        }
        Client guest = clientRepository.getClientByName(lobby.getGuest().get()).get();
        guest.getMessageChannel().sendResponse(response);
    }

    private void handleLeaveLobby(RequestLobbyStatus message, Client sender) {
        String lobbyName = message.getContent().getName();

        Lobby lobby = lobbyController.leaveLobby(lobbyName, sender).get();

        sender.getMessageChannel().sendResponse(new ResponseLobbyStatus());

        String hostName = lobby.getHost().get();
        Optional<Client> optionalHost = clientRepository.getClientByName(hostName);
        optionalHost.ifPresent(
                client -> client.getMessageChannel().sendResponse(convertLobbyToResponseMessage(lobby))
        );
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
