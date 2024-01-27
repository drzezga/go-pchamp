package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class MoveIsRecorded implements Rule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        game.getMoves().add(move);
    }
}
