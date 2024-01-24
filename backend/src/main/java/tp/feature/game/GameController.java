package tp.feature.game;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.lobby.Lobby;
import tp.model.Board;
import tp.model.messages.shared.GameSettings;

import java.util.HashMap;
import java.util.Optional;

@Log
@Controller
public class GameController {
    private HashMap<Client, Game> clientToGameMap = new HashMap<>();
    private final ClientRepository clientRepository;

    @Autowired
    public GameController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Game startGameFromLobby(Lobby lobby, GameSettings settings) {
        boolean isPlayingAgainstBot = settings.getBotOpponent();
        if(isPlayingAgainstBot) {
            return startGameAgainstBot(lobby, settings);
        } else {
            return startGameAgainstHuman(lobby, settings);
        }
    }

    private Game startGameAgainstHuman(Lobby lobby, GameSettings settings) {
        var game = new Game();
        game.setGameSettings(settings);

        game.setBoard(new Board(settings.getSize()));

        Client host = clientRepository.getClientByName(lobby.getHost().get()).get();
        Client guest = clientRepository.getClientByName(lobby.getGuest().get()).get();

        String hostName = host.getName();
        String guestName = guest.getName();

        if(settings.getStartingPlayer().equals(hostName)) {
            game.setWhitePlayer(hostName);
            game.setBlackPlayer(guestName);
        } else {
            game.setBlackPlayer(guestName);
            game.setWhitePlayer(hostName);
        }

        clientToGameMap.put(host, game);
        clientToGameMap.put(guest, game);

        return game;
    }

    public Optional<Game> getGameByClient(Client client) {
        return Optional.ofNullable(clientToGameMap.get(client));
    }

    public void destroyGame(Game game) {
        Client blackPlayer = clientRepository.getClientByName(game.getBlackPlayer()).get();
        Client whitePlayer = clientRepository.getClientByName(game.getWhitePlayer()).get();

        clientToGameMap.remove(blackPlayer);
        clientToGameMap.remove(whitePlayer);
    }

    private Game startGameAgainstBot(Lobby lobby, GameSettings settings) {
        // TODO: Implement playing against a bot
        throw new UnsupportedOperationException("Playing against a bot hasnt yet been implemented");
    }
}
