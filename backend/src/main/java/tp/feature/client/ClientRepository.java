package tp.feature.client;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ClientRepository {
    private HashMap<String, Client> nameToPlayerMap = new HashMap<>();
    private HashMap<ClientMessageChannel, Client> messageChannelPlayerMap = new HashMap<>();

    @Autowired
    public ClientRepository(ClientEvents clientEvents) {
        clientEvents.getClientConnectEvent().subscribe(this::addClient);
        clientEvents.getClientDisconnectEvent().subscribe(this::removeClient);
    }

    public void addClient(Client player) {
        messageChannelPlayerMap.put(player.getMessageChannel(), player);
        nameToPlayerMap.put(player.getName(), player);
    }

    public void renamePlayer(Client player, String newName) {
        removeClient(player);
        addClient(new Client(player.getMessageChannel(), newName));
    }

    public void removeClient(Client player) {
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
