package tp.feature.game;

import org.junit.jupiter.api.Test;
import tp.feature.game.rules.CapturingMechanic;
import tp.feature.game.rules.CurrentPlayerIsSwitched;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRuleApplicatorTests {
    @Test
    public void tryApplyWorksWithMultipleRules() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0},
                {0, 2, 1, 0, 0},
                {0, 1, 2, 1, 0},
                {0, 2, 1, 2, 0},
                {0, 0, 0, 0, 0}
        });
        Move move = new Move(new Position(1, 1), Piece.BLACK);
        GameRuleApplicator gameRuleApplicator = new GameRuleApplicator();
        gameRuleApplicator.addRule(new CurrentPlayerIsSwitched());
        gameRuleApplicator.addRule(new CapturingMechanic());
        gameRuleApplicator.tryApplyMove(game, move);

        // Assert
        assertEquals(Piece.WHITE, game.getCurrentPlayer());

        GameState expected = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0},
                {0, 2, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 2, 1, 2, 0},
                {0, 0, 0, 0, 0}
        });

        assertEquals(expected.getCurrentBoardState(), game.getCurrentBoardState());
    }
}
