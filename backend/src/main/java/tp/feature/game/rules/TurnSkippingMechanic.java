package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class TurnSkippingMechanic implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        if(move.position().isPresent()) {
            game.setNumConsecutiveSkips(0);
            return;
        }

        game.setNumConsecutiveSkips(
                game.getNumConsecutiveSkips() + 1
        );

        if(game.getNumConsecutiveSkips() == 2) {
            game.setFinished(true);
        }
    }
}
