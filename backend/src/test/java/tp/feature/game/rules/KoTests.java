package tp.feature.game.rules;

import org.junit.jupiter.api.Test;
import tp.feature.game.GameState;
import tp.feature.game.GameTestingUtils;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KoTests {
    private final CapturingMechanic capturingMechanic = new CapturingMechanic();
    private final Move someBlackMove = new Move(
            new Position(0, 0),
            Piece.BLACK
    );
    private final Move someWhiteMove = new Move(
            new Position(1, 1),
            Piece.WHITE
    );
    @Test
    public void testKo() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        });

        capturingMechanic.apply(game, someBlackMove);

        KoRule rule = new KoRule();

        rule.apply(game, someWhiteMove);

        Board expected = GameTestingUtils.createBoardFromArray(new int[][] {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }
}
