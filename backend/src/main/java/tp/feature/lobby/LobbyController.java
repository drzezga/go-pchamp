package tp.feature.lobby;

import org.springframework.stereotype.Component;
import tp.feature.client.Client;
import tp.model.messages.response.ResponseLobbyStatus;

import java.util.*;

@Component
public class LobbyController {
    private HashMap<String, Lobby> nameToLobbyMap = new HashMap<>();
    private HashMap<Client, Lobby> clientToLobbyMap = new HashMap<>();

    public Lobby addNewLobby(String lobbyName) {
        var lobby = new Lobby(lobbyName);
        nameToLobbyMap.put(lobbyName, lobby);
        return lobby;
    }

    public Collection<Lobby> getAllLobbies() {
        return nameToLobbyMap.values();
    }

    public Optional<Lobby> getLobbyByName(String lobbyName) {
        return Optional.ofNullable(nameToLobbyMap.get(lobbyName));
    }

    public Lobby joinLobby(String lobbyName, Client client) {
        Optional<Lobby> optionalLobby = getLobbyByName(lobbyName);

        Lobby lobby;
        if(optionalLobby.isPresent()) {
            lobby = optionalLobby.get();
            lobby.setGuest(Optional.of(client));
        } else {
            lobby = addNewLobby(lobbyName);
            lobby.setHost(Optional.of(client));
        }

        clientToLobbyMap.put(client, lobby);
        return lobby;
    }

    public Optional<Lobby> leaveLobby(Client client) {
        Optional<Lobby> optionalLobby = getLobbyByClient(client);

        if(optionalLobby.isEmpty()) {
            return Optional.empty();
        }

        Lobby lobby = optionalLobby.get();
        String senderName = client.getName();

        if(lobby.getHost().equals(Optional.of(senderName))) {
            lobby.setHost(lobby.getGuest());
            lobby.setGuest(Optional.empty());
        } else {
            lobby.setGuest(Optional.empty());
        }

        clientToLobbyMap.remove(client);

        return optionalLobby;
    }

    public Optional<Lobby> getLobbyByClient(Client client) {
        return Optional.ofNullable(clientToLobbyMap.get(client));
    }
}
