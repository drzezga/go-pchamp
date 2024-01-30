package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Board;
import tp.model.Move;

public class MustPlayWithinBounds implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        if(move.getPosition() == null) {
            return;
        }

        Board board = game.getCurrentBoardState();
        if (move.getPosition().x() >= board.getSize() || move.getPosition().y() >= board.getSize()) {
            throw new MustPlayWithinBounds.Exception("Must play within bounds!");
        }
    }

    public class Exception extends RuleBrokenException {
        public Exception(String message) {
            super(message);
        }
    }
}
