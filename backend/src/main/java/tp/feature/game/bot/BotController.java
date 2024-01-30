package tp.feature.game.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.feature.client.Client;
import tp.feature.client.ClientMessageChannel;
import tp.feature.client.ClientRepository;
import tp.feature.game.Game;
import tp.feature.game.GameState;
import tp.feature.game.rules.RuleBrokenException;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.response.ResponseMessage;
import tp.model.messages.shared.GameSettings;

import java.util.List;
import java.util.Random;

@Controller
public class BotController {
    private static final String BOT_PLAYER_NAME = "bot";
    public static Client BOT_CLIENT = new Client(
            new ClientMessageChannel() {
                @Override
                public <T extends ResponseMessage> void sendResponse(T response) {}
            },
            BOT_PLAYER_NAME
    );

    private final ClientRepository clientRepository;
    private final Random random = new Random();


    @Autowired
    public BotController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void registerBotPlayer() {
        clientRepository.addClient(BOT_CLIENT);
    }

    public void scheduleMove(Game game) {
        Move move;
        List<Position> capturedPositions;
        try {
            move = getRandomMove(game.getGameState());
            capturedPositions = game.getGameState().makeMove(move);
        } catch(RuleBrokenException e) {
            move = getSkippingMove(game.getGameState());
            capturedPositions = game.getGameState().makeMove(move);
        }

//        gameController.broadcastMessageToPlayers(game,
//                new ResponseGameMove(
//                        MessageStatus.OK,
//                        new ResponseGameMove.Content(
//                                BOT_PLAYER_NAME,
//                                move.getPosition(),
//                                capturedPositions
//                        )
//                )
//        );
    }

    private Move getSkippingMove(GameState game) {
        return new Move(null, getBotPiece(game.getSettings()));
    }

    private Move getRandomMove(GameState game) {
        int boardSize = game.getSettings().getSize();

        return new Move(
                new Position(
                        random.nextInt(boardSize),
                        random.nextInt(boardSize)
                ),
                getBotPiece(game.getSettings())
        );
    }

    private Piece getBotPiece(GameSettings gameSettings) {
        String startingPlayer = gameSettings.getStartingPlayer();

        if(startingPlayer.equals(BOT_PLAYER_NAME)) {
            return Piece.BLACK;
        } else {
            return Piece.WHITE;
        }
    }
}
