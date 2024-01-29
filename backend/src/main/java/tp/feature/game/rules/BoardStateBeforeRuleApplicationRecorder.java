package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class BoardStateBeforeRuleApplicationRecorder implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        game.setBoardStateBeforeRuleApplication(game.getCurrentBoardState().clone());
    }
}
