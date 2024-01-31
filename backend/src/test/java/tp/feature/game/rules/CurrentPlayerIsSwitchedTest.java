package tp.feature.game.rules;

import org.junit.jupiter.api.Test;
import tp.feature.game.GameState;
import tp.feature.game.GameTestingUtils;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentPlayerIsSwitchedTest {
    @Test
    public void testApply() {
        // Arrange
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 1, 0},
                {1, 2, 1},
                {0, 1, 0}
        });
        Move move = new Move(new Position(0, 0), Piece.BLACK);
        CurrentPlayerIsSwitched currentPlayerIsSwitched = new CurrentPlayerIsSwitched();
        // Act
        currentPlayerIsSwitched.apply(game, move);
        // Assert
        assertEquals(Piece.WHITE, game.getCurrentPlayer());
    }
}
