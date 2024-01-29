package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class KoRule implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        if(move.position().isEmpty()) {
            return;
        }

        if(game.getPreviousBoardState().equals(game.getCurrentBoardState())) {
            throw new KoRule.Exception();
        }
    }

    public static class Exception extends RuleBrokenException {
        public Exception() {
            super("This move violates the ko rule");
        }
    }
}
