package tp.game.rules;

import tp.game.Board;
import tp.game.Game;
import tp.game.Move;

import java.util.Optional;

public interface Rule {
    /**
     * Modify the board according to a rule, returning `Optional.EMPTY` if the rule was broken.
     * @param board the current board state
     * @param move the move to be made
     */
    void modify(Board board, Move move) throws RuleBrokenException;
}
