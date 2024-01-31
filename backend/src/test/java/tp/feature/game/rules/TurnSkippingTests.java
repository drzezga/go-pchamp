package tp.feature.game.rules;

import org.junit.jupiter.api.Test;
import tp.feature.game.GameState;
import tp.feature.game.GameTestingUtils;
import tp.model.Move;
import tp.model.Piece;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnSkippingTests {
    @Test
    public void testTurnSkipping() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        });

        TurnSkippingMechanic rule = new TurnSkippingMechanic();

        rule.apply(game, new Move(null, Piece.WHITE));
        rule.apply(game, new Move(null, Piece.BLACK));

        assertTrue(game.isFinished());
    }
}
