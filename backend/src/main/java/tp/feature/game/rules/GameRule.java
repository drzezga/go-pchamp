package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

import java.io.Serializable;

public interface GameRule extends Serializable {
    void apply(GameState game, Move move) throws RuleBrokenException;
}
