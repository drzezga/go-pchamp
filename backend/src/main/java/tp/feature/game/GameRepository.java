package tp.feature.game;

import org.springframework.stereotype.Repository;
import tp.feature.client.Client;
import tp.feature.lobby.Lobby;
import tp.model.messages.shared.GameSettings;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class GameRepository {
    private final HashMap<Client, Game> clientToGameMap = new HashMap<>();

    public Optional<Game> getGameByClient(Client client) {
        Game game = clientToGameMap.get(client);
        return Optional.ofNullable(game);
    }

    public Game registerGameAgainstAHuman(Lobby lobby, GameSettings gameSettings) {
        var game = new Game();
        game.setGameState(new GameState(gameSettings));

        Client host = lobby.getHost().get();
        Client guest = lobby.getGuest().get();

        if(gameSettings.getStartingPlayer().equals(host.getName())) {
            game.setBlackPlayer(host);
            game.setWhitePlayer(guest);
        } else {
            game.setWhitePlayer(host);
            game.setBlackPlayer(guest);
        }

        clientToGameMap.put(host, game);
        clientToGameMap.put(guest, game);

        return game;
    }

    public void removeGame(Game game) {
        clientToGameMap.remove(game.getBlackPlayer());
        clientToGameMap.remove(game.getWhitePlayer());
    }
}
