package tp.feature.player;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class PlayerRegistry {
    private HashMap<String, Player> nameToPlayerMap = new HashMap<>();
    private HashMap<PlayerMessageChannel, Player> messageChannelPlayerMap = new HashMap<>();

    public void addPlayer(Player player) {
        messageChannelPlayerMap.put(player.getMessageChannel(), player);
        nameToPlayerMap.put(player.getName(), player);
    }

    public void renamePlayer(Player player, String newName) {
        removePlayer(player);
        addPlayer(new Player(player.getMessageChannel(), newName));
    }

    public void removePlayer(Player player) {
        messageChannelPlayerMap.remove(player.getMessageChannel());
        nameToPlayerMap.remove(player.getName());
    }

    public Optional<Player> getPlayerByName(String playerName) {
        return Optional.ofNullable(nameToPlayerMap.get(playerName));
    }

    public Optional<Player> getPlayerByMessageChannel(PlayerMessageChannel messageChannel) {
        return Optional.ofNullable(messageChannelPlayerMap.get(messageChannel));
    }
}
