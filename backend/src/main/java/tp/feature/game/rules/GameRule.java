package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

import java.io.Serializable;

public interface GameRule extends Serializable {
    void apply(Game game, Move move) throws RuleBrokenException;
}
