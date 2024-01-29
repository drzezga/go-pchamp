package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Board;
import tp.model.Move;

public class MustPlayWithinBounds implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        if(move.position().isEmpty()) {
            return;
        }

        Board board = game.getCurrentBoardState();
        if (move.position().get().x() >= board.getSize() || move.position().get().y() >= board.getSize()) {
            throw new MustPlayWithinBounds.Exception("Must play within bounds!");
        }
    }

    public class Exception extends RuleBrokenException {
        public Exception(String message) {
            super(message);
        }
    }
}
