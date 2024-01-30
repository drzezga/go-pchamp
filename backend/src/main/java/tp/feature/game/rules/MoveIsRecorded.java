package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

public class MoveIsRecorded implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        game.getMoves().add(move);
    }
}
