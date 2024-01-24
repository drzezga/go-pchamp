package tp.feature.game.rules;

import tp.model.Board;
import tp.model.Move;

public interface Rule {
    /**
     * Modify the board according to a rule, returning `Optional.EMPTY` if the rule was broken.
     * @param board the current board state
     * @param move the move to be made
     */
    void modify(Board board, Move move) throws RuleBrokenException;
}
