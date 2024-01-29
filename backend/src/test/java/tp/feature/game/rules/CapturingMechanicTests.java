package tp.feature.game.rules;


import org.junit.jupiter.api.Test;
import tp.feature.game.Game;
import tp.feature.game.GameTestingUtils;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import static org.junit.jupiter.api.Assertions.*;

public class CapturingMechanicTests {
    private CapturingMechanic capturingMechanic = new CapturingMechanic();
    private Move someBlackMove = new Move(
            new Position(0, 0),
            Piece.BLACK
    );
    private Move someWhiteMove = new Move(
            new Position(0, 0),
            Piece.WHITE
    );

    @Test
    public void testCenterCapture() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        });

        capturingMechanic.apply(game, someBlackMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }

    @Test
    public void testCornerCaptures() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {2, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        });

        capturingMechanic.apply(game, someBlackMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }

    @Test
    public void testCaptureNextToWall() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {1, 2, 1}
        });

        capturingMechanic.apply(game, someBlackMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 1}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }

    @Test
    public void testBigGroupCapture() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 2, 1, 0},
                {0, 0, 0, 0, 1, 2, 2, 1, 0},
                {0, 0, 0, 1, 2, 2, 2, 2, 1},
                {0, 0, 1, 2, 2, 2, 1, 1, 1}
        });

        capturingMechanic.apply(game, someBlackMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 1, 1}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }

    @Test
    public void testDoubleGroupCapture() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 2, 1, 2, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0}
        });

        capturingMechanic.apply(game, someBlackMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }

    @Test
    public void testWhiteCanCaptureBlack() {
        Game game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 2, 2, 2, 0},
                {2, 1, 1, 1, 2},
                {2, 1, 1, 1, 2},
                {2, 1, 1, 1, 2},
                {0, 2, 2, 2, 0}
        });

        capturingMechanic.apply(game, someWhiteMove);

        Board expected = GameTestingUtils.createBoardFromStrings(new int[][] {
                {0, 2, 2, 2, 0},
                {2, 0, 0, 0, 2},
                {2, 0, 0, 0, 2},
                {2, 0, 0, 0, 2},
                {0, 2, 2, 2, 0}
        });

        assertEquals(expected, game.getCurrentBoardState());
    }
}
