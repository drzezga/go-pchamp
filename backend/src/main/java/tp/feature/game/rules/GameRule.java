package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public interface GameRule {
    void apply(Game game, Move move) throws RuleBrokenException;
}
