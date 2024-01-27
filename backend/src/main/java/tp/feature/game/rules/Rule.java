package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Board;
import tp.model.Move;

public interface Rule {
    void apply(Game game, Move move) throws RuleBrokenException;
}
