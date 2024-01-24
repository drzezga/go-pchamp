package tp.feature.game.requestHandlers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.game.Game;
import tp.feature.game.GameController;
import tp.feature.lobby.LobbyController;
import tp.model.messages.request.RequestGameLeave;
import tp.model.messages.response.ResponseGameLeave;

@Log
@Controller
public class GameLeaveHandler implements RequestMessageHandler<RequestGameLeave> {
    private final GameController gameController;
    private final ClientRepository clientRepository;

    @Autowired
    public GameLeaveHandler(GameController gameController, ClientRepository clientRepository) {
        this.gameController = gameController;
        this.clientRepository = clientRepository;
    }

    @Override
    public void onMessage(RequestGameLeave message, Client sender) {
        Game game = gameController.getGameByClient(sender).get();

        Client whitePlayer = clientRepository.getClientByName(game.getWhitePlayer()).get();
        Client blackPlayer = clientRepository.getClientByName(game.getBlackPlayer()).get();

        var response = new ResponseGameLeave();
        whitePlayer.getMessageChannel().sendResponse(response);
        blackPlayer.getMessageChannel().sendResponse(response);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GAME_LEAVE;
    }
}
