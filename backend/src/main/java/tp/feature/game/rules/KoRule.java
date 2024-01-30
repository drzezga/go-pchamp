package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

public class KoRule implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        if(move.getPosition() == null) {
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
