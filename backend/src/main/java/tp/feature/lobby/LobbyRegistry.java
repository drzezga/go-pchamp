package tp.feature.lobby;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LobbyRegistry {
    private HashMap<String, Lobby> nameToLobbyMap = new HashMap<>();

    public Lobby registerNewLobby(String lobbyName) {
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
}
