package tp.feature.game.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.MessageStatus;
import tp.feature.client.Client;
import tp.feature.client.ClientMessageChannel;
import tp.feature.client.ClientRepository;
import tp.feature.game.Game;
import tp.feature.game.GameState;
import tp.feature.game.rules.RuleBrokenException;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.response.ResponseGameMove;
import tp.model.messages.response.ResponseMessage;
import tp.model.messages.shared.GameSettings;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private final ScheduledExecutorService executor =  Executors.newSingleThreadScheduledExecutor();

    @Autowired
    public BotController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void registerBotPlayer() {
        clientRepository.addClient(BOT_CLIENT);
    }

    public void scheduleMove(Game game) {
        executor.schedule(() -> move(game), 1, TimeUnit.SECONDS);
    }

    public void move(Game game) {
        Move move;
        List<Position> capturedPositions;
        try {
            move = getRandomMove(game.getGameState());
            capturedPositions = game.getGameState().makeMove(move);
        } catch(RuleBrokenException e) {
            move = getSkippingMove(game.getGameState());
            capturedPositions = game.getGameState().makeMove(move);
        }

        var response = new ResponseGameMove(
                MessageStatus.OK,
                new ResponseGameMove.Content(
                        BOT_PLAYER_NAME,
                        move.getPosition(),
                        capturedPositions
                )
        );

        game.getBlackPlayer().getMessageChannel().sendResponse(response);
        game.getWhitePlayer().getMessageChannel().sendResponse(response);
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
