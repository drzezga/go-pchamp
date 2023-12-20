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
            assertEquals(Piece.BLACK, b.getPiece(2, 3));
            assertEquals(Piece.BLACK, b.getPiece(2, 3));
            assertNull(b.getPiece(8, 8));
        };

        RuleValidator validator = new RuleValidator();
        validator.addRule(rule);

        validator.validate(board, null);
    }

    @Test
    void validatorCanModifyBoard() throws RuleBrokenException {
        Board board = new Board(9);

        Rule rule1 = (b, m) -> {
            b.setPiece(2, 3, Piece.WHITE);
            b.setPiece(3, 3, Piece.WHITE);
        };

        Rule rule2 = (b, m) -> b.setPiece(3, 3, Piece.BLACK);

        RuleValidator validator = new RuleValidator();

        validator.addRule(rule1);
        validator.addRule(rule2);

        validator.validate(board, null);

        assertEquals(Piece.WHITE, board.getPiece(2, 3));
        assertEquals(Piece.BLACK, board.getPiece(3, 3));

    }
}
