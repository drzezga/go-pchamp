package tp.game.rules;

import tp.game.Board;
import tp.game.Move;

public class MustPlayWithinBounds implements Rule {
    @Override
    public void modify(Board board, Move move) throws RuleBrokenException {
        if (move.position().x() >= board.getSize() || move.position().y() >= board.getSize()) {
            throw new RuleBrokenException("Must play within bounds!");
        }
    }
}
