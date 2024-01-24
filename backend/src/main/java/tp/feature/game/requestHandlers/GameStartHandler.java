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

        String hostName = lobby.getHost().get();

        if(!hostName.equals(sender.getName())) {
            log.warning(String.format(
                    "Player %s tried to start a game but is not the host of the lobby",
                    sender.getName()
            ));
            return;
        }

        gameController.startGameFromLobby(lobby, message.getContent());

        Client host = clientRepository.getClientByName(hostName).get();
        Client guest = clientRepository.getClientByName(lobby.getGuest().get()).get();

        var response = new ResponseGameStart(MessageStatus.OK);
        response.setContent(message.getContent());

        host.getMessageChannel().sendResponse(response);
        guest.getMessageChannel().sendResponse(response);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_START;
    }
}
