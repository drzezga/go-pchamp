package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class PreviousBoardStateRecorder implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        game.setPreviousBoardState(game.getBoardStateBeforeRuleApplication().clone());
    }
}
