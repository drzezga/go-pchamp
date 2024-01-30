package tp.feature.game.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.feature.client.Client;
import tp.feature.client.ClientEvents;
import tp.feature.game.Game;
import tp.feature.game.GameEvents;
import tp.feature.game.GameRepository;

import java.util.Optional;

@Component
public class ClientDisconnectHandler {
    private final GameRepository gameRepository;
    private final GameEvents gameEvents;

    @Autowired
    public ClientDisconnectHandler(ClientEvents clientEvents, GameRepository gameRepository, GameEvents gameEvents) {
        this.gameRepository = gameRepository;
        this.gameEvents = gameEvents;
        clientEvents.getClientDisconnectEvent().subscribe(this::handleClientDisconnected);
    }

    public void handleClientDisconnected(Client client) {
        Optional<Game> game = gameRepository.getGameByClient(client);

        if(game.isEmpty()) {
            return;
        }

        gameEvents.getClientLeaveEventEvent().dispatch(new GameEvents.ClientLeaveEvent(
                game.get(),
                client
        ));
    }
}
