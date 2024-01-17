package tp.game;

import org.junit.jupiter.api.Test;
import tp.model.Board;
import tp.model.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void cloneClonesBoardContent() {
        Board board = new Board(9);

        board.setPiece(3, 3, Piece.BLACK);

        Board board1 = board.clone();

        board1.setPiece(2, 3, Piece.WHITE);
        board.setPiece(2, 3, Piece.BLACK);

        assertEquals(board1.getPiece(2, 3), Piece.WHITE);
        assertEquals(board.getPiece(2, 3), Piece.BLACK);
        assertEquals(board.getPiece(2, 3), Piece.BLACK);
        assertEquals(board.getPiece(3, 3), Piece.BLACK);
    }
}
