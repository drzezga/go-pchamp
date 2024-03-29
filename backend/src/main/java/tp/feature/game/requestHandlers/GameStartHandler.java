package tp.feature.game.requestHandlers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.game.GameController;
import tp.feature.lobby.Lobby;
import tp.feature.lobby.LobbyController;
import tp.model.messages.request.RequestGameStart;
import tp.model.messages.response.ResponseGameStart;

@Log
@Controller
public class GameStartHandler implements RequestMessageHandler<RequestGameStart> {
    private final LobbyController lobbyController;
    private final ClientRepository clientRepository;
    private final GameController gameController;

    @Autowired
    public GameStartHandler(LobbyController lobbyController, ClientRepository clientRepository, GameController gameController) {
        this.lobbyController = lobbyController;
        this.clientRepository = clientRepository;
        this.gameController = gameController;
    }

    @Override
    public void onMessage(RequestGameStart message, Client sender) {
        Lobby lobby = lobbyController.getLobbyByClient(sender).get();

        String hostName = lobby.getHost().get().getName();

        if(!hostName.equals(sender.getName())) {
            throw new GuestCannotStartGameException();
        }

        gameController.startGameFromLobby(lobby, message.getContent());

        // TODO: Validate correct number of players
        Client host = lobby.getHost().get();

        var response = new ResponseGameStart(MessageStatus.OK);
        response.setContent(message.getContent());

        host.getMessageChannel().sendResponse(response);
        lobby.getGuest().ifPresent(
                guest -> guest.getMessageChannel().sendResponse(response)
        );
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_START;
    }

    public static class GuestCannotStartGameException extends RuntimeException {
        public GuestCannotStartGameException() {
            super("Guest cannot start a game. Only host can do so");
        }
    }
}
