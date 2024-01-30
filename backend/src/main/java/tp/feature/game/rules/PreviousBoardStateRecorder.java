package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

public class PreviousBoardStateRecorder implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        game.setPreviousBoardState(game.getBoardStateBeforeRuleApplication().clone());
    }
}
