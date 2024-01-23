package tp.feature.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ClientRepository {
    private HashMap<String, Client> nameToPlayerMap = new HashMap<>();
    private HashMap<ClientMessageChannel, Client> messageChannelPlayerMap = new HashMap<>();

    public void addPlayer(Client player) {
        messageChannelPlayerMap.put(player.getMessageChannel(), player);
        nameToPlayerMap.put(player.getName(), player);
    }

    public void renamePlayer(Client player, String newName) {
        removePlayer(player);
        addPlayer(new Client(player.getMessageChannel(), newName));
    }

    public void removePlayer(Client player) {
        messageChannelPlayerMap.remove(player.getMessageChannel());
        nameToPlayerMap.remove(player.getName());
    }

    public Optional<Client> getClientByName(String playerName) {
        return Optional.ofNullable(nameToPlayerMap.get(playerName));
    }

    public Optional<Client> getPlayerByMessageChannel(ClientMessageChannel messageChannel) {
        return Optional.ofNullable(messageChannelPlayerMap.get(messageChannel));
    }
}
