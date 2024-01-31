package tp.feature.game.bot;

import tp.feature.game.GameState;
import tp.model.Position;

import java.util.Random;

public class RandomSpotStrategy implements BotStrategy {
    private final Random random = new Random();

    @Override
    public Position tryGenerateMovePosition(GameState gameState) throws RuntimeException {
        int boardSize = gameState.getSettings().getSize();

        return new Position(
                random.nextInt(boardSize),
                random.nextInt(boardSize)
        );
    }
}
