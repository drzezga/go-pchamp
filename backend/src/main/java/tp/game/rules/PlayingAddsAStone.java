package tp.game.rules;


import tp.game.Board;
import tp.game.Move;

public class PlayingAddsAStone implements Rule {
    @Override
    public void modify(Board board, Move move) {
        if (move.position() != null) {
            board.setPiece(move.position(), move.piece());
        }
    }
}
