package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

public class BoardStateBeforeRuleApplicationRecorder implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        game.setBoardStateBeforeRuleApplication(game.getCurrentBoardState().clone());
    }
}
