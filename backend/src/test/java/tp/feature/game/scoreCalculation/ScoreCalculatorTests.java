package tp.feature.game.scoreCalculation;


import org.junit.jupiter.api.Test;
import tp.feature.game.GameState;
import tp.feature.game.GameTestingUtils;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreCalculatorTests {

    @Test
    public void testBasicCase() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {2, 1, 2},
                {1, 0, 1},
                {2, 1, 2}
        });

        GameScoreCalculator calculator = new GameScoreCalculator();
        GameScores result = calculator.calculateScores(game);

        assertEquals(new GameScores(6.5f, 1f), result);
    }

    @Test
    public void testBasicCaseButBiggerBoard() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0},
                {0, 2, 1, 2, 0},
                {0, 1, 0, 1, 0},
                {0, 2, 1, 2, 0},
                {0, 0, 0, 0, 0}
        });

        GameScoreCalculator calculator = new GameScoreCalculator();
        GameScores result = calculator.calculateScores(game);

        assertEquals(new GameScores(6.5f, 1f), result);
    }
}
