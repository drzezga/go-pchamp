package tp.game.core.rules;

import tp.game.core.Board;
import tp.game.core.Move;

public interface Rule {
    /**
     * Modify the board according to a rule, returning `Optional.EMPTY` if the rule was broken.
     * @param board the current board state
     * @param move the move to be made
     */
    void modify(Board board, Move move) throws RuleBrokenException;
}
