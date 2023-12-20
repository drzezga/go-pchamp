package tp.game.rules;

import org.junit.jupiter.api.Test;
import tp.game.Board;
import tp.game.Move;
import tp.game.Piece;
import tp.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RuleValidatorTest {
    @Test
    void validatorCanAccessBoard() throws RuleBrokenException {
        Board board = new Board(9);
        board.setPiece(2, 3, Piece.BLACK);
        board.setPiece(3, 3, Piece.WHITE);

        Rule rule = (b, m) -> {
            assertEquals(b.getPiece(2, 3), Piece.BLACK);
            assertEquals(b.getPiece(2, 3), Piece.BLACK);
            assertNull(b.getPiece(8, 8));
        };

        RuleValidator validator = new RuleValidator();
        validator.addRule(rule);

        validator.validate(board, new Move(new Position(3, 2), Piece.BLACK));
    }
}
