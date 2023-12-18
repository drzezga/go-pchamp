package tp.game.rules;

import tp.game.Board;
import tp.game.Move;

import java.util.Optional;

public interface Rule {
    /**
     * Modify the board according to a rule, returning `Optional.EMPTY` if the rule was broken.
     * @param board the current state of the board
     * @param move the move to be made
     * @return the new board state or `Optional.EMPTY` if the move is illegal.
     */
    Optional<Board> modify(Board board, Move move);
}
