package tp.feature.game.bot;

import tp.feature.game.Game;
import tp.feature.game.GameState;
import tp.model.Position;

public interface BotStrategy {
    Position tryGenerateMovePosition(GameState gameState) throws RuntimeException;
}
