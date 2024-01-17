package tp.feature.game.core.rules;

import tp.model.Board;
import tp.model.Move;

public class MustPlayWithinBounds implements Rule {
    @Override
    public void modify(Board board, Move move) throws RuleBrokenException {
        if (move.position().x() >= board.getSize() || move.position().y() >= board.getSize()) {
            throw new RuleBrokenException("Must play within bounds!");
        }
    }
}
